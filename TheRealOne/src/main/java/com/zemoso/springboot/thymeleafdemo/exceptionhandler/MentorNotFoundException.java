package com.zemoso.springboot.thymeleafdemo.exceptionhandler;

public class MentorNotFoundException extends RuntimeException {
    public MentorNotFoundException(String message) {
        super(message);
    }

    public MentorNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public MentorNotFoundException(Throwable cause) {
        super(cause);
    }
}
