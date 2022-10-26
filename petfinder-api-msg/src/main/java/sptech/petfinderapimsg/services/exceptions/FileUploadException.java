package sptech.petfinderapimsg.services.exceptions;

public class FileUploadException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    
    public FileUploadException() {
        super("O arquivo não pode ser salvo, tente novamente mais tarde");
    }
}
