package petfinder.petfinderapi.controladores.exception;

import java.time.Instant;

public class StandardError {
    
    // attributes
    private static final long serialVersionUID = 1L;
    protected Instant timestamp;
    protected Integer status;
    protected String error;
    protected String message;
    protected String path;    

    // constructor
    public StandardError() {}

    public StandardError(Integer status, String error, String message, String path) {
        this.timestamp = Instant.now();
        this.status = status;
        this.error = error;
        this.message = message;
        this.path = path;
    }

    // getters and setters
    public static long getSerialversionuid() {
        return serialVersionUID;
    }
    public Instant getTimestamp() {
        return timestamp;
    }
    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }
    public Integer getStatus() {
        return status;
    }
    public void setStatus(Integer status) {
        this.status = status;
    }
    public String getError() {
        return error;
    }
    public void setError(String error) {
        this.error = error;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public String getPath() {
        return path;
    }
    public void setPath(String path) {
        this.path = path;
    }
    
}
