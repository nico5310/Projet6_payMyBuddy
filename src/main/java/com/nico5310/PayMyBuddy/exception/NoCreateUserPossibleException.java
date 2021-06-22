package com.nico5310.PayMyBuddy.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED, reason = "Parameters to save user are invalid")
public class NoCreateUserPossibleException extends Exception {
    public NoCreateUserPossibleException(String message) {

        super(message);
    }
}
