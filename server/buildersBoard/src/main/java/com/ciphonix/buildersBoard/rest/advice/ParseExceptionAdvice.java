package com.ciphonix.buildersBoard.rest.advice;

import javassist.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.text.ParseException;

/**
 * <h3>Short Description</h3>
 * This Advice works like an Interceptor. If the RestController throws an Exception while processing an request, this advice catch the exception and handle this.
 *
 * This Exception listen to {@link ParseException} and returns the message of the exception. Additionally, the Handler add the Http-Code "INTERNAL_SERVER_ERROR" (500).
 *
 * @see ParseException
 * @see HttpStatus#INTERNAL_SERVER_ERROR
 * @see ControllerAdvice
 * @author wermar
 */
@ControllerAdvice
public class ParseExceptionAdvice {

    @ResponseBody
    @ExceptionHandler(ParseException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    String exceptionHandler(NotFoundException ve) {
        return "5099 " + ve.getMessage();
    }
}
