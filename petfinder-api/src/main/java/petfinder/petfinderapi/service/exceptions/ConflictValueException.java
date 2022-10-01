package petfinder.petfinderapi.service.exceptions;

public class ConflictValueException extends RuntimeException {
    
    private static final long serialVersionUID = 1L;

    public ConflictValueException(String message) {
        super(message);
    }

    public ConflictValueException(String field, String value) {
        super(String.format("O %s '%s' já está adastrado", field, value));
    }

}
