package tech.nbtsinfo.whatsapp.model.request;

import java.io.Serializable;

public class EvolutionApiRequestBody implements Serializable {
    private String number;
    private String text;

    public EvolutionApiRequestBody(String number, String text) {
        this.number = number;
        this.text = text;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
