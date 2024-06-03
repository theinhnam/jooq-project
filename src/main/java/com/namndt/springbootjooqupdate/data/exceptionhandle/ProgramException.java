package com.namndt.springbootjooqupdate.data.exceptionhandle;

import com.namndt.springbootjooqupdate.data.exception.AccountNotFoundException;
import com.namndt.springbootjooqupdate.data.exception.PasswordIncorrectException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ProgramException {

    @ExceptionHandler(AccountNotFoundException.class)
    public ResponseEntity<Object> accountNotFoundException(AccountNotFoundException accountNotFoundException){
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(accountNotFoundException.getMessage());
    }

    @ExceptionHandler(PasswordIncorrectException.class)
    public ResponseEntity<Object> PasswordIncorectException(PasswordIncorrectException passwordIncorrectException){
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body("Password incorrect");
    }
}
