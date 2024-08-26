package tech.nbtsinfo.whatsapp.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Response;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.multipart.MultipartFile;
import tech.nbtsinfo.whatsapp.enums.MessageType;
import tech.nbtsinfo.whatsapp.model.request.EvolutionApiRequestBody;
import tech.nbtsinfo.whatsapp.model.request.GroqRequestBody;
import tech.nbtsinfo.whatsapp.model.WebhookEvolution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.nbtsinfo.whatsapp.model.response.TranscriptionResponse;
import tech.nbtsinfo.whatsapp.service.feign.EvolutionApiClient;
import tech.nbtsinfo.whatsapp.service.feign.GroqClient;
import tech.nbtsinfo.whatsapp.utils.Base64MultipartFile;
import tech.nbtsinfo.whatsapp.validator.WebhookRequestValidator;

import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;

@Service
public class WhatsAppService {

    @Autowired
    private Environment environment;

    @Value("${groq.client.token:}")
    private String groqClientToken = this.environment.getProperty("groq.client.token");

    @Autowired
    private EvolutionApiClient evolutionApiClient;

    @Autowired
    private GroqClient groqClient;

    @Autowired
    private WebhookRequestValidator webhookRequestValidator;


    public void handleMessage(WebhookEvolution request) {
        Boolean isValid = this.webhookRequestValidator.isValid(request);
        if (!isValid) return;


        String aiResponse = this.processMessageReceived(request);
        if (aiResponse == null || aiResponse.isEmpty())  return;

        this.sendTextMessage(request, aiResponse);

    }

    private String processMessageReceived(WebhookEvolution request) {
        String aiResponse = "";
        if (request.getMessage() == null) return "";

        if (request.getMessage().getType().equals(MessageType.TEXT)) {
            String message = request.getMessage().getText();
            aiResponse = this.formatSendMessage(message);
        }

        if (request.getMessage().getType().equals(MessageType.AUDIO)) {
            aiResponse = this.transcriptionMessage(request);
        }

        return aiResponse;
    }

    private void sendTextMessage(WebhookEvolution request, String aiResponse) {
        if (request == null || aiResponse == null || aiResponse.isEmpty() || request.getRemoteJid() == null || request.getApikey() == null || request.getInstance() == null) return;

        String numberPhone = request.getRemoteJid().replace("@s.whatsapp.net", "");
        if (numberPhone.isEmpty()) return;

        EvolutionApiRequestBody requestBody = new EvolutionApiRequestBody(numberPhone, aiResponse);
        this.evolutionApiClient.sendTextMessage(request.getInstance(), requestBody, request.getApikey());
    }

    public String transcriptionMessage(WebhookEvolution request) {
        if (request == null) return null;
        MultipartFile audioFile = this.convertBase64ToMultipartFile(request.getMessage().getBase64());
        GroqRequestBody requestBody = new GroqRequestBody(audioFile);

        try (Response response = this.sendTranscriptionRequest(requestBody)) {
            if (response == null || response.status() != 200 || response.body() == null) return null;

            String responseText = this.extractTextFromResponseBody(response);
            return formatSendMessage(responseText);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private Response sendTranscriptionRequest(GroqRequestBody requestBody) throws IOException {
        return groqClient.transcription(groqClientToken, requestBody.getModel(), requestBody.getTemperature(), requestBody.getResponseFormat(), requestBody.getLanguage(), requestBody.getFile());
    }


    private String extractTextFromResponseBody(Response response) throws IOException {
        try (InputStream bodyStream = response.body().asInputStream()) {
            String responseBody = new String(bodyStream.readAllBytes());
            return parseTranscriptionResponse(responseBody).getText();
        }
    }

    private TranscriptionResponse parseTranscriptionResponse(String responseBody) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(responseBody, TranscriptionResponse.class);
    }

    public String formatSendMessage(String messageText) {
        if (messageText == null || messageText.trim().isEmpty()) {
            return "";
        }
        return "*Transcrição Automática*💬:\n_" + messageText.trim() + "_";
    }

    private MultipartFile convertBase64ToMultipartFile(String base64) {
        String fileName = "audio.mp3";
        String contentType = "audio/mpeg";

        byte[] audioBytes = Base64.getDecoder().decode(base64);
        return new Base64MultipartFile(audioBytes, null, fileName, contentType);
    }

}