package com.ciphonix.buildersBoard.rest.advice;

import javassist.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * <h3>Description</h3>
 * This Advice works like an Interceptor. If the RestController throws an Exception while processing an request, this advice catch the exception and handle this.
 *
 * This Exception listen to {@link NotFoundException} and returns the message of the exception. Additionally, the Handler add the Http-Code "NOT_FOUND" (404).
 *
 * @see NotFoundException
 * @see HttpStatus#NOT_FOUND
 * @see ControllerAdvice
 * @author wermar
 */
@ControllerAdvice
public class NotFoundAdvice {

    @ResponseBody
    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String exceptionHandler(NotFoundException ve) {
        return ve.getMessage();
    }
}
