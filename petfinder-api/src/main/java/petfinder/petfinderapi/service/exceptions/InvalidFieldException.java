package petfinder.petfinderapi.service.exceptions;

public class InvalidFieldException extends RuntimeException {
    
    private static final long serialVersionUID = 1L;

    public InvalidFieldException(String field, String message) {
        super(message);
    }
}
