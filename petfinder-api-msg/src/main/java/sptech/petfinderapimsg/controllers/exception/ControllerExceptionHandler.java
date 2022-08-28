package sptech.petfinderapimsg.controllers.exception;

import static org.springframework.http.ResponseEntity.*;
import org.springframework.http.ResponseEntity;
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import sptech.petfinderapimsg.services.exceptions.EntityNotFoundException;
import sptech.petfinderapimsg.services.exceptions.IdNotFoundException;
import sptech.petfinderapimsg.services.exceptions.NoContentException;

@ControllerAdvice
public class ControllerExceptionHandler {
    
    // 404 not found
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<StandardError> entityNotFound(EntityNotFoundException e, HttpServletRequest request) {
        StandardError err = new StandardError(
            HttpStatus.NOT_FOUND.value(), 
            "Resource not found", 
            e.getMessage(), 
            request.getRequestURI()
        );
        return status(404).body(err);
    }

    // 400 bad request - foreign key not found
    @ExceptionHandler(IdNotFoundException.class)
    public ResponseEntity<StandardError> idNotFound(IdNotFoundException e, HttpServletRequest request) {
        IdNotFoundError err = new IdNotFoundError(
            e.getFieldName(), 
            e.getId(), 
            request.getRequestURI()
        );
        return status(400).body(err);
    }

    // 204 no content
    @ExceptionHandler(NoContentException.class)
    public ResponseEntity<StandardError> noContent(NoContentException e, HttpServletRequest request) {
        StandardError err = new StandardError(
            204, 
            "No Content", 
            e.getMessage(), 
            request.getRequestURI()
        );
        return status(204).body(err);
    }
}
