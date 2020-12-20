package com.ciphonix.buildersBoard.rest.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.ValidationException;
import java.text.ParseException;

/**
 * <h3>Short Description</h3>
 * This Advice works like an Interceptor. If the RestController throws an Exception while processing an request, this advice catch the exception and handle this.
 *
 * This Exception listen to {@link ValidationException} and returns the message of the exception. Additionally, the Handler add the Http-Code "BAD_REQUEST" (400).
 *
 * @see ValidationException
 * @see HttpStatus#BAD_REQUEST
 * @see ControllerAdvice
 * @author wermar
 */
@ControllerAdvice
public class ValidationAdvice {

    @ResponseBody
    @ExceptionHandler(ValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String exceptionHandler(ValidationException ve) {
        return ve.getMessage();
    }
}
