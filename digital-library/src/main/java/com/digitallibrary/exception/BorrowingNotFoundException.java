package com.digitallibrary.exception;

public class BorrowingNotFoundException extends RuntimeException {
    public BorrowingNotFoundException(String message) {
        super(message);
    }
}
