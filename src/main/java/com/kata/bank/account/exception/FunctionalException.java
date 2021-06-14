package com.kata.bank.account.exception;

public class FunctionalException extends Exception {

    public FunctionalException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public FunctionalException(final String message) {
        super(message);
    }
}
