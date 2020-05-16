package com.litesoftwares.covidcheckbot.api.exception;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.litesoftwares.covidcheckbot.api.TwitterApiError;

public class TwitterApiException extends RuntimeException {
    @JsonIgnore
    private TwitterApiError error;

    public TwitterApiException(TwitterApiError error){
        this.error = error;
    }

    public TwitterApiException(Throwable cause){
        super(cause);
    }

    public TwitterApiException(String message, Throwable cause){
        super(message,cause);
    }

    public TwitterApiError getError() {
        return error;
    }

    public void setError(TwitterApiError error) {
        this.error = error;
    }

    @Override
    public String getMessage() {
        return
                error != null ?
                        error.toString() :
                        super.getMessage();
    }
}
