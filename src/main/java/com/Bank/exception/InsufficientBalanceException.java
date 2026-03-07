package com.Bank.exception;

/**
 * Custom exception thrown when an account
 * does not have enough balance to complete
 * a withdrawal or fund transfer operation.
 */
public class InsufficientBalanceException extends RuntimeException {

    /**
     * Default constructor
     */
    public InsufficientBalanceException() {
        super("Insufficient balance in the account.");
    }

    /**
     * Constructor with custom message
     */
    public InsufficientBalanceException(String message) {
        super(message);
    }

    /**
     * Constructor with message and cause
     */
    public InsufficientBalanceException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructor with cause
     */
    public InsufficientBalanceException(Throwable cause) {
        super(cause);
    }
}