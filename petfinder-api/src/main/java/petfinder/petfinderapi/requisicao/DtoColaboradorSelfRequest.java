package petfinder.petfinderapi.requisicao;

public class DtoColaboradorSelfRequest {
    
    // attribute
    private String nome;
    private String email;
    private String senha;

    // constructor
    public DtoColaboradorSelfRequest() {}

    // getters and setters
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getSenha() {
        return senha;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }
}
