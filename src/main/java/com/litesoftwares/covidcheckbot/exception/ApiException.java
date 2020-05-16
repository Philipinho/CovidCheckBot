package com.litesoftwares.covidcheckbot.exception;

public class ApiException extends RuntimeException{

    public ApiException(String message){
        super(message);
    }
}
