package com.controller;

import com.bean.ErrorResponse;
import com.exception.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExeceptionController {

    @ExceptionHandler(BadRequestException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse badRequestHandler(BadRequestException e) {
        return new ErrorResponse(e.getMessage());
    }


    @ExceptionHandler(Exception.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse exceptionHandler(Exception e) {
        e.printStackTrace();
        return new ErrorResponse(e.getMessage());
    }
}
