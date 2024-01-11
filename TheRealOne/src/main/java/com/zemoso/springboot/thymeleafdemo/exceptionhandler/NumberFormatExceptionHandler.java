package com.zemoso.springboot.thymeleafdemo.exceptionhandler;

public class NumberFormatExceptionHandler extends RuntimeException{
    public NumberFormatExceptionHandler(String message) {
        super(message);
    }

    public NumberFormatExceptionHandler(String message, Throwable cause) {
        super(message, cause);
    }

    public NumberFormatExceptionHandler(Throwable cause) {
        super(cause);
    }
}
