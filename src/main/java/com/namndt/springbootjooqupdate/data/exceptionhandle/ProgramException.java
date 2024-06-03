package com.namndt.springbootjooqupdate.data.exceptionhandle;

import com.namndt.springbootjooqupdate.data.exception.AccessDeniedException;
import com.namndt.springbootjooqupdate.data.exception.AccountNotFoundException;
import com.namndt.springbootjooqupdate.data.exception.NotFoundException;
import com.namndt.springbootjooqupdate.data.exception.PasswordIncorrectException;
import com.namndt.springbootjooqupdate.data.responses.DFResponse;
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

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<DFResponse> AccessDeniedException(AccessDeniedException accessDeniedException){
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(new DFResponse("403", "Bạn không có quyền truy cập", null));
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<DFResponse> NotFoundException(){
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new DFResponse(HttpStatus.NOT_FOUND.toString(), "Data not found", null));
    }
}
