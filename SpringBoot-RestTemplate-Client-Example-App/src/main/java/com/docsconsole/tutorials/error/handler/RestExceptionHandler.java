package com.docsconsole.tutorials.error.handler;

import com.docsconsole.tutorials.error.Error;
import com.docsconsole.tutorials.exception.CourseNotFoundException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String error = "Malformed JSON request";
        return buildResponseEntity(new Error(HttpStatus.BAD_REQUEST, error, ex));
    }

    @ExceptionHandler(value = {CourseNotFoundException.class})
    protected ResponseEntity handleNotFound(final RuntimeException ex, final WebRequest request) {
        String error = "Course not been found.";
        return buildResponseEntity(new Error(HttpStatus.NOT_FOUND, error, ex));

    }

    private ResponseEntity<Object> buildResponseEntity(Error error) {
        return new ResponseEntity<>(error, error.getStatus());
    }
}
