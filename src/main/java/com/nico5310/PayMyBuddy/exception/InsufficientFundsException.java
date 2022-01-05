package com.nico5310.PayMyBuddy.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED, reason = "Insufficient Funds")
public class InsufficientFundsException extends Exception{
    public  InsufficientFundsException(String message) {
        super(message);
    }

}
