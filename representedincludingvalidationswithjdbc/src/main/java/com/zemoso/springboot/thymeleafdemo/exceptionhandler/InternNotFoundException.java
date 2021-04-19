package com.zemoso.springboot.thymeleafdemo.exceptionhandler;

public class InternNotFoundException extends RuntimeException{
    public InternNotFoundException(String message) {
        super(message);
    }

    public InternNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public InternNotFoundException(Throwable cause) {
        super(cause);
    }
}
