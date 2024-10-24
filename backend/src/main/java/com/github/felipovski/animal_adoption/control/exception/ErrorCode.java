package com.github.felipovski.animal_adoption.control.exception;

public enum ErrorCode {

    ANIMAL_ADOPTION_0001("Animal não encontrado para o ID informado.");

    private final String message;

    ErrorCode(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
