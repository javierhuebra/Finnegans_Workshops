package com.finnegans.demodario.exception.custom;

public class EmptyElementException extends RuntimeException{
    private static final String DESCRIPTION = "Empty Element Exception (400)";

    public EmptyElementException(String detail){
        super(DESCRIPTION + ". " + detail);
    }
}
