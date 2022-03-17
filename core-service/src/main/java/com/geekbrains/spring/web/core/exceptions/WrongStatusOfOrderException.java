package com.geekbrains.spring.web.core.exceptions;

public class WrongStatusOfOrderException extends RuntimeException{
    public WrongStatusOfOrderException(String message) {
        super(message);
    }
}
