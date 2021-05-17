package com.youmesh.buildersBoard.rest.advice;

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
 * This Exception listen to {@link NullPointerException} and returns the message of the exception. Additionally, the Handler add the Http-Code "NO_CONTENT" (204).
 *
 * @see NullPointerException
 * @see HttpStatus#NO_CONTENT
 * @see ControllerAdvice
 * @author wermar
 */
@ControllerAdvice
public class NullPointerAdvice {

    @ResponseBody
    @ExceptionHandler(NullPointerException.class)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    String exceptionHandler(NullPointerException ve) {
        return ve.getMessage();
    }
}
