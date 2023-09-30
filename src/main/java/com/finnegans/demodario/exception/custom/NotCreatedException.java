package com.finnegans.demodario.exception.custom;

public class NotCreatedException extends RuntimeException{
    private static final String DESCRIPTION = "Not Created Exception (400)";

    public NotCreatedException(String detail){
        super(DESCRIPTION + ". " + detail);
    }
}

