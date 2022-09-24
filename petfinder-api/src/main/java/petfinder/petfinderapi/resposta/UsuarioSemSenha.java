package petfinder.petfinderapi.resposta;

import petfinder.petfinderapi.entidades.Endereco;
import petfinder.petfinderapi.entidades.Instituicao;
import petfinder.petfinderapi.entidades.Usuario;

public class UsuarioSemSenha {

    // atributos
    private int id;
    private String nome;
    private String email;
    private String nivelAcesso;
    private Endereco endereco;
    private Instituicao fkInstituicao;
    private boolean logado;

    // construtores
    public UsuarioSemSenha() {

    }
    public UsuarioSemSenha(Usuario usuario) {
        this(usuario, usuario.getEndereco());
    }
    public UsuarioSemSenha(Usuario usuario, Endereco endereco) {
        this(
            usuario.getId(),
            usuario.getNome(),
            usuario.getEmail(),
            usuario.getNivelAcesso(),
            endereco,
            usuario.getInstituicao(),
            usuario.isLogado()
        );
    }
    public UsuarioSemSenha(int id, String nome, String email, String nivelAcesso, Endereco endereco,
                           Instituicao fkInstituicao, boolean logado) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.nivelAcesso = nivelAcesso;
        this.endereco = endereco;
        this.fkInstituicao = fkInstituicao;
        this.logado = logado;
    }

    public UsuarioSemSenha(UsuarioSemSenha usuario, Endereco endereco) {
        this(
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getNivelAcesso(),
                endereco,
                usuario.getFkInstituicao(),
                usuario.isLogado()
        );
    }

    // getters a setters
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
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
    public String getNivelAcesso() {
        return nivelAcesso;
    }
    public void setNivelAcesso(String nivelAcesso) {
        this.nivelAcesso = nivelAcesso;
    }
    public Endereco getEndereco() {
        return endereco;
    }
    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
    public Instituicao getFkInstituicao() {
        return fkInstituicao;
    }
    public void setFkInstituicao(Instituicao fkInstituicao) {
        this.fkInstituicao = fkInstituicao;
    }
    public boolean isLogado() {
        return logado;
    }
    public void setLogado(boolean logado) {
        this.logado = logado;
    }
}
