package sptech.petfinderapimsg.services.exceptions;

public class IdNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private int id;
    private String fieldName;
    
    public IdNotFoundException(int id, String fieldName) {
        super(String.format("Id %d not found in field '%s'", id, fieldName));
        this.id = id;
        this.fieldName = fieldName;
    }

    // getters
    public int getId() {
        return id;
    }
    public String getFieldName() {
        return fieldName;
    }
}
