package com.nico5310.PayMyBuddy.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Not Found")
public class NoFoundException extends RuntimeException {
    public NoFoundException(String message) {
        super(message);
    }
}
