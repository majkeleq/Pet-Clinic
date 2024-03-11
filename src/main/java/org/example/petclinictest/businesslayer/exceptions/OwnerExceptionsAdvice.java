package org.example.petclinictest.businesslayer.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
public class OwnerExceptionsAdvice {

    @ResponseBody
    @ExceptionHandler(OwnerNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String ownerNotFoundHandler(OwnerNotFoundException ex) {
        return ex.getMessage();
    }
}
