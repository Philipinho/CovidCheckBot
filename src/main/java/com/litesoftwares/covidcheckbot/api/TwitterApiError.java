package com.litesoftwares.covidcheckbot.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TwitterApiError {
    @JsonProperty("code")
    private int code;
    @JsonProperty("message")
    private String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "TwitterApiError{" +
                "code=" + code +
                ", message='" + message + '\'' +
                '}';
    }
}
