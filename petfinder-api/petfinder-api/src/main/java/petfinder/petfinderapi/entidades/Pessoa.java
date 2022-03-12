package petfinder.petfinderapi.entidades;

public abstract class Pessoa implements Validavel{
    private String nome;
    private String email;
    private String senha;
    private TodosCargo cargo;
    private Endereco endereco;

    public Pessoa(String nome, String email, String senha) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    public Pessoa(String nome, String email, String senha, TodosCargo cargo) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.cargo = cargo;
    }

    public Pessoa(String nome, String email, String senha, Endereco endereco) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.endereco = endereco;
    }

//    MÉTODOS

    public Boolean autenticarLogin(String email, String senha){
        return null;
    }

    public abstract Boolean validar();

//    GETTERS E SETTERS

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

    public TodosCargo getCargo() {
        return cargo;
    }
    public void setCargo(TodosCargo cargo) {
        this.cargo = cargo;
    }

    public Endereco getEndereco() {
        return endereco;
    }
    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
}
