package tech.nbtsinfo.whatsapp.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class TranscriptionResponse implements Serializable {
    @JsonProperty("text")
    private String text;

    @JsonProperty("x_groq")
    private XGroq xGroq;

    public TranscriptionResponse() {
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public XGroq getxGroq() {
        return xGroq;
    }

    public void setxGroq(XGroq xGroq) {
        this.xGroq = xGroq;
    }

    public static class XGroq {
        private String id;

        public XGroq() {
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }
}
