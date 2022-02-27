package com.geekbrains.spring.web.core.exceptions;

public class IncorrectAddressException extends RuntimeException{
    public IncorrectAddressException(String message) {
        super(message);
    }
}
