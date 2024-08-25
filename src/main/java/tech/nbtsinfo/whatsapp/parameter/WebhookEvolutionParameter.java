package tech.nbtsinfo.whatsapp.parameter;

import tech.nbtsinfo.whatsapp.enums.MessageType;
import tech.nbtsinfo.whatsapp.model.ContentMessage;
import tech.nbtsinfo.whatsapp.model.WebhookEvolution;

import java.util.Map;

public class WebhookEvolutionParameter {
    private Body body;


    public Body getBody() {
        return body;
    }

    public void setBody(Body body) {
        this.body = body;
    }

    public static class Body {
        private String instance;
        private String apikey;
        private Data data;

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

        public Data getData() {
            return data;
        }

        public void setData(Data data) {
            this.data = data;
        }

        public static class Data {
            private Key key;
            private Message message;
            private String messageType;

            public String getMessageType() {
                return messageType;
            }

            public void setMessageType(String messageType) {
                this.messageType = messageType;
            }

            public Key getKey() {
                return key;
            }

            public void setKey(Key key) {
                this.key = key;
            }

            public Message getMessage() {
                return message;
            }

            public void setMessage(Message message) {
                this.message = message;
            }

            public static class Key {
                private String remoteJid;

                public String getRemoteJid() {
                    return remoteJid;
                }

                public void setRemoteJid(String remoteJid) {
                    this.remoteJid = remoteJid;
                }
            }

            public static class Message {
                private String conversation;
                private Map<String, Object> audioMessage;
                private Map<String, Object> imageMessage;
                private Map<String, Object> documentMessage;
                private Map<String, Object> extendedTextMessage;
                private String base64;

                public String getBase64() {
                    return base64;
                }

                public void setBase64(String base64) {
                    this.base64 = base64;
                }

                public String getConversation() {
                    return conversation;
                }

                public void setConversation(String conversation) {
                    this.conversation = conversation;
                }

                public Map<String, Object> getAudioMessage() {
                    return audioMessage;
                }

                public void setAudioMessage(Map<String, Object> audioMessage) {
                    this.audioMessage = audioMessage;
                }

                public Map<String, Object> getImageMessage() {
                    return imageMessage;
                }

                public void setImageMessage(Map<String, Object> imageMessage) {
                    this.imageMessage = imageMessage;
                }

                public Map<String, Object> getDocumentMessage() {
                    return documentMessage;
                }

                public void setDocumentMessage(Map<String, Object> documentMessage) {
                    this.documentMessage = documentMessage;
                }

                public Map<String, Object> getExtendedTextMessage() {
                    return extendedTextMessage;
                }

                public void setExtendedTextMessage(Map<String, Object> extendedTextMessage) {
                    this.extendedTextMessage = extendedTextMessage;
                }

            }
        }
    }

    public WebhookEvolution toModel() {
        WebhookEvolution webhookEvolution = new WebhookEvolution();
        if (this.getBody() != null) {
            webhookEvolution.setInstance(this.getBody().getInstance());
            webhookEvolution.setApikey(this.getBody().getApikey());
            if (this.getBody().getData() != null && this.getBody().getData().getKey() != null) {
                webhookEvolution.setSender(this.getBody().getData().getKey().getRemoteJid());
                webhookEvolution.setRemoteJid(this.getBody().getData().getKey().getRemoteJid());
            }
            ContentMessage contentMessage = new ContentMessage();
            if (this.getBody().getData().getMessage() != null) {
                contentMessage.setText(this.getBody().getData().getMessage().getConversation());
                contentMessage.setType(MessageType.fromValue(this.getBody().getData().getMessageType()));
                contentMessage.setBase64(this.getBody().getData().getMessage().getBase64());
            }
            webhookEvolution.setMessage(contentMessage);
        }
        return webhookEvolution;
    }

}
