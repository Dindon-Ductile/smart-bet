package com.smartbet.demo.server.controllers;

import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@ResponseStatus(NOT_FOUND)
public class NotFoundException extends Exception {
    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException() {
        super();
    }
}
