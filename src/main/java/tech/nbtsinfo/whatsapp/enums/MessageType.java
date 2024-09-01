package tech.nbtsinfo.whatsapp.enums;

public enum MessageType {
    TEXT("extendedTextMessage"),
    IMAGE("imageMessage"),
    VIDEO("videoMessage"),
    AUDIO("audioMessage"),
    DOCUMENT("documentMessage");

    private String value;

    MessageType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static MessageType fromValue(String value) {
        for (MessageType messageType : MessageType.values()) {
            if (messageType.getValue().equals(value)) {
                return messageType;
            }
        }
        return null;
    }
}
