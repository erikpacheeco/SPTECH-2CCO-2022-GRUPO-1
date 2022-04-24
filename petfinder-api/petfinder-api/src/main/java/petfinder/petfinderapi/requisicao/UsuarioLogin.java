package petfinder.petfinderapi.requisicao;

public class UsuarioLogin {

    // atributos
    private String email;
    private String senha;

    // construtores
    public UsuarioLogin(String email, String senha) {
        this.email = email;
        this.senha = senha;
    }

    // métodos
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
