package com.mynotes.dto.responses;

public class NoteCreatedResponse {

    private String response;

    public NoteCreatedResponse(String response) {
        this.response = response;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}
