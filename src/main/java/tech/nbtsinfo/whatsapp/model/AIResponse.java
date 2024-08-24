package tech.nbtsinfo.whatsapp.model;

import java.io.Serializable;

public class AIResponse implements Serializable {
    private String result;

    public AIResponse() {
    }

    public AIResponse(String result) {
        this.result = result;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
