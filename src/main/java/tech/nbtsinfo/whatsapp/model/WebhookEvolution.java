package tech.nbtsinfo.whatsapp.model;

import java.io.Serializable;

public class WebhookEvolution implements Serializable {
    private String instance;
    private String apikey;
    private String remoteJid;
    private ContentMessage message;
    private String sender;
    private String contactName;
    private String base64;


    public WebhookEvolution() {
    }

    public String getBase64() {
        return base64;
    }

    public void setBase64(String base64) {
        this.base64 = base64;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getInstance() {
        return instance;
    }

    public void setInstance(String instance) {
        this.instance = instance;
    }

    public String getApikey() {
        return apikey;
    }

    public void setApikey(String apikey) {
        this.apikey = apikey;
    }

    public String getRemoteJid() {
        return remoteJid;
    }

    public void setRemoteJid(String remoteJid) {
        this.remoteJid = remoteJid;
    }

    public ContentMessage getMessage() {
        return message;
    }

    public void setMessage(ContentMessage message) {
        this.message = message;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }
}
