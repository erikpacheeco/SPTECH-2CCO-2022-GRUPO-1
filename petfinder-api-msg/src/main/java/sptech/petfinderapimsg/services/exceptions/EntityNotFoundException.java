package sptech.petfinderapimsg.services.exceptions;

public class EntityNotFoundException extends RuntimeException {
    
    private static final long serialVersionUID = 1L;
    
    public EntityNotFoundException(int id) {
        super(String.format("Id %d not found", id));
    }

}
