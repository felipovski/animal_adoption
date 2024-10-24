package com.github.felipovski.animal_adoption.control.exception;

import org.springframework.http.HttpStatus;

public abstract class CustomException extends RuntimeException  {

    final String errorCode;
    final String message;
    final HttpStatus httpStatus;

    protected CustomException(String errorCode, String message, HttpStatus httpStatus) {
        this.errorCode = errorCode;
        this.message = message;
        this.httpStatus = httpStatus;
    }

    public String getErrorCode() {
        return errorCode;
    }


    @Override
    public String getMessage() {
        return message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
