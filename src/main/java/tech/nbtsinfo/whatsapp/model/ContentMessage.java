package tech.nbtsinfo.whatsapp.model;

import tech.nbtsinfo.whatsapp.enums.MessageType;

public class ContentMessage {
    private MessageType type;
    private String text;
    private String base64;

    public ContentMessage() {
    }

    public MessageType getType() {
        return type;
    }

    public void setType(MessageType type) {
        this.type = type;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getBase64() {
        return base64;
    }

    public void setBase64(String base64) {
        this.base64 = base64;
    }
}
