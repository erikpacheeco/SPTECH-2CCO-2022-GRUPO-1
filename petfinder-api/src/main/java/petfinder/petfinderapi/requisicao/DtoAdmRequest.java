package petfinder.petfinderapi.requisicao;

import javax.validation.constraints.Email;
import petfinder.petfinderapi.entidades.Convertible;
import petfinder.petfinderapi.entidades.Usuario;

public class DtoAdmRequest implements Convertible<Usuario> {
    
    // atributes
    private String nome;
    @Email(message = "formato de email inválido")
    private String email;
    private String senha;
    private DtoInstituicaoRequest instituicao;

    // constructor
    public DtoAdmRequest() {}

    // methods
    public Usuario convert() {
        Usuario adm = new Usuario();
        adm.setEmail(this.getEmail());
        adm.setNivelAcesso("adm");
        adm.setNome(this.getNome());
        adm.setSenha(this.getSenha());
        adm.setInstituicao(this.getInstituicao().convert());
        return adm;
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
    public String getSenha() {
        return senha;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }
    public DtoInstituicaoRequest getInstituicao() {
        return instituicao;
    }
    public void setInstituicao(DtoInstituicaoRequest instituicao) {
        this.instituicao = instituicao;
    }
}
