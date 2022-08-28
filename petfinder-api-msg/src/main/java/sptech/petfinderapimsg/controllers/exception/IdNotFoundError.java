package sptech.petfinderapimsg.controllers.exception;

public class IdNotFoundError extends StandardError {

    // attributes
    protected Integer id;
    protected String fieldName;

    // constructor
    public IdNotFoundError(String fieldName, int id, String path) {
        super(
            400, 
            "Id not found",
            String.format("'%s' with id %d not found", fieldName, id), 
            path
        );
        this.id = id;
        this.fieldName = fieldName;
    }

    // getters and setters
    public Integer getId() {
        return id;
    }
    public String getFieldName() {
        return fieldName;
    }
}
