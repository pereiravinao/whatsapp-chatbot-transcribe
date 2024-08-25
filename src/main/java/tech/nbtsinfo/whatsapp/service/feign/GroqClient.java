package tech.nbtsinfo.whatsapp.service.feign;

import feign.Headers;
import feign.Param;
import feign.RequestLine;
import feign.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import tech.nbtsinfo.whatsapp.configuration.FeignConfig;

@FeignClient(name = "groqClient", url = "https://api.groq.com", configuration = FeignConfig.class)
public interface GroqClient {


    @PostMapping(value = "/openai/v1/audio/transcriptions", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    Response transcription(@RequestHeader("Authorization") String bearerToken,
                           @RequestPart("model") String model,
                           @RequestPart("temperature") String temperature,
                           @RequestPart("response_format") String responseFormat,
                           @RequestPart("language") String language,
                           @RequestPart("file") MultipartFile file
                           );
}
