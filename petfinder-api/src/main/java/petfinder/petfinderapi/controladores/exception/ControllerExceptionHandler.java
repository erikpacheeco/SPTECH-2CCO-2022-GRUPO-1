package petfinder.petfinderapi.controladores.exception;

import static org.springframework.http.ResponseEntity.*;
import org.springframework.http.ResponseEntity;
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import petfinder.petfinderapi.service.exceptions.ConflictValueException;
import petfinder.petfinderapi.service.exceptions.EntityNotFoundException;
import petfinder.petfinderapi.service.exceptions.IdNotFoundException;
import petfinder.petfinderapi.service.exceptions.InvalidFieldException;
import petfinder.petfinderapi.service.exceptions.NoContentException;

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

    // 400 bad request - invalid field value
    @ExceptionHandler(InvalidFieldException.class)
    public ResponseEntity<StandardError> invalidFieldException(InvalidFieldException e, HttpServletRequest request) {
        StandardError err = new StandardError(
            HttpStatus.BAD_REQUEST.value(), 
            "Campo inválido", 
            e.getMessage(), 
            request.getRequestURI()
        );
        return status(400).body(err);
    }

    // 400 bad request - invalid field value
    @ExceptionHandler(ConflictValueException.class)
    public ResponseEntity<StandardError> ConflictValueException(ConflictValueException e, HttpServletRequest request) {
        StandardError err = new StandardError(
            HttpStatus.CONFLICT.value(), 
            "Valor já existente", 
            e.getMessage(), 
            request.getRequestURI()
        );
        return status(409).body(err);
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
