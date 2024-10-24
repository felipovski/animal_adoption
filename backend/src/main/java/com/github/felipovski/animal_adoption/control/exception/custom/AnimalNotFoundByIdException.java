package com.github.felipovski.animal_adoption.control.exception.custom;

import com.github.felipovski.animal_adoption.control.exception.CustomException;
import com.github.felipovski.animal_adoption.control.exception.ErrorCode;
import org.springframework.http.HttpStatus;

public class AnimalNotFoundByIdException extends CustomException {

    public AnimalNotFoundByIdException() {
        super(ErrorCode.ANIMAL_ADOPTION_0001.toString(), ErrorCode.ANIMAL_ADOPTION_0001.getMessage(), HttpStatus.CONFLICT);
    }
}
