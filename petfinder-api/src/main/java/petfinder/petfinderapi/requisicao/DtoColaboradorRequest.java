package petfinder.petfinderapi.requisicao;

import javax.validation.constraints.Email;
import petfinder.petfinderapi.entidades.Convertible;
import petfinder.petfinderapi.entidades.Instituicao;
import petfinder.petfinderapi.entidades.Usuario;

public class DtoColaboradorRequest implements Convertible<Usuario> {
    
    // attributes
    private String nome;
    @Email(message = "Estrutura de e-mail inválido")
    private String email;
    private String cargo;
    private String senha;
    private int instituicaoId;

    // constructor
    public DtoColaboradorRequest() {}

    @Override
    public String toString() {
        return "DtoColaboradorRequest [nome=" + nome + ", email=" + email + ", cargo=" + cargo + ", senha=" + senha
                + ", instituicaoId=" + instituicaoId + "]";
    }

    // methods
    public Usuario convert() {
        Usuario entity = new Usuario();
        entity.setId(null);
        entity.setEmail(this.email);
        entity.setLogado(false);
        entity.setNivelAcesso(this.cargo);
        entity.setNome(this.nome);
        entity.setSenha(this.senha);
        entity.setInstituicao(new Instituicao());
        entity.getInstituicao().setId(this.getInstituicaoId());
        return entity;
    }

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
    public String getCargo() {
        return cargo;
    }
    public void setCargo(String cargo) {
        this.cargo = cargo;
    }
    public String getSenha() {
        return senha;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }
    public int getInstituicaoId() {
        return instituicaoId;
    }
    public void setInstituicaoId(int instituicaoId) {
        this.instituicaoId = instituicaoId;
    }    
}
