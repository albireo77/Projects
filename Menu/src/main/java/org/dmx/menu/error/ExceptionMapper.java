package org.dmx.menu.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class ExceptionMapper {


    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ApplicationError> handleValidationException(ValidationException e) {
        ApplicationError error = new ApplicationError(e.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

}
