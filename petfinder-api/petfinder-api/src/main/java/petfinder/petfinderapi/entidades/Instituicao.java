package petfinder.petfinderapi.entidades;

import java.util.Objects;

public class Instituicao implements Validavel{
    private String nome;
    private String email;
    private String telefone;
    private Endereco endereco;

    public Instituicao(String nome,
                       String email,
                       String telefone,
                       Endereco endereco) {
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.endereco = endereco;
    }

    //    MÉTODOS

    @Override
    public Boolean validar() {
        Boolean boolNome = Objects.isNull(this.nome) || this.nome.isEmpty() || this.nome.isBlank();
        Boolean boolEmail = Objects.isNull(this.email) || this.email.isEmpty() || this.email.isBlank();
        Boolean boolTelefone = Objects.isNull(this.telefone) || this.telefone.isEmpty() || this.telefone.isBlank();
        Boolean boolEndereco = !endereco.validar();

        if(!(boolNome || boolEmail || boolTelefone || boolEndereco)){
            return true;
        }
        return false;
    }

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

    public String getTelefone() {
        return telefone;
    }
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Endereco getEndereco() {
        return endereco;
    }
    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

}
