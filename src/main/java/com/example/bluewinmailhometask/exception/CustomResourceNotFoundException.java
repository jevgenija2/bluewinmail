package com.example.bluewinmailhometask.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus
public class CustomResourceNotFoundException extends RuntimeException{

    public static final long serialVersionUID = 1L;

    public CustomResourceNotFoundException(String message){
        super(message);
    }
}
