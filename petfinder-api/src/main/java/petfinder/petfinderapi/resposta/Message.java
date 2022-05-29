package petfinder.petfinderapi.resposta;

public class Message {

    // attributes
    private String message;
    private String field;

    // constructors
    public Message(String message) {
        this.message = message;
    }
    public Message(String message, String field) {
        this(message);
        this.field = field;
    }

    // getters and setters
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public String getField() {
        return field;
    }
    public void setField(String field) {
        this.field = field;
    }
}
