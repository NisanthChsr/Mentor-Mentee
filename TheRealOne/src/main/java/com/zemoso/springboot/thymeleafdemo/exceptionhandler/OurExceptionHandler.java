package com.zemoso.springboot.thymeleafdemo.exceptionhandler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class OurExceptionHandler {
    @ExceptionHandler
    public String handleException(InternNotFoundException exception){
        return "intern-not-found";
    }

    @ExceptionHandler
    public String handleException(MentorNotFoundException exception){
        return "mentor-not-found";
    }
    @ExceptionHandler
    public String handleException(NumberFormatExceptionHandler exception){
        return "number-not-found.html";
    }
}
