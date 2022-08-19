package petfinder.petfinderapi.service.exceptions;

public class NoContentException extends RuntimeException {
    
    private static final long serialVersionUID = 1L;
    
    public NoContentException(String entityName) {
        super(String.format("There is no %s yet", entityName));
    }

}
