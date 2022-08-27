package petfinder.petfinderapi.resposta;

import petfinder.petfinderapi.entidades.Usuario;

public class ColaboradorSimples {
    
    // attributes
    private Integer id;
    private String nome;
    private String email;
    private String cargo;
    private String instituicao;
    
    // constructor
    public ColaboradorSimples() {}
    public ColaboradorSimples(Usuario user) {
        this.id = user.getId();
        this.nome = user.getNome();
        this.cargo = user.getNivelAcesso();
        this.email = user.getEmail();
        this.instituicao = user.getInstituicao().getNome();
    }

    // getters and setters
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getCargo() {
        return cargo;
    }
    public void setCargo(String cargo) {
        this.cargo = cargo;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getInstituicao() {
        return instituicao;
    }
    public void setInstituicao(String instituicao) {
        this.instituicao = instituicao;
    }
    
}
