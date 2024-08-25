package tech.nbtsinfo.whatsapp.validator;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import tech.nbtsinfo.whatsapp.model.WebhookEvolution;

@Component
public class WebhookRequestValidator {

    @Value("${whatsapp.group.id.family}")
    private String familyGroupId;

    @Value("${whatsapp.group.id.home}")
    private String homeGroupId;

    public Boolean isValid(WebhookEvolution request) {
        if (request == null) {
            return false;
        }
        if (request.getRemoteJid() == null) {
            return false;
        }
        if (request.getRemoteJid().contains("@g.us") && (!request.getRemoteJid().equals(familyGroupId) || !request.getRemoteJid().equals(homeGroupId))) {
            return false;
        }
        if (request.getMessage() == null) {
            return false;
        }
        return true;
    }
}
