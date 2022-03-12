package petfinder.petfinderapi.entidades;

public class Colaborador extends Pessoa {
    private Instituicao instituicao;

    public Colaborador(String nome,
                       String email,
                       String senha,
                       Instituicao instituicao) {
        super(nome, email, senha);
        this.instituicao = instituicao;
    }

    public Instituicao getInstituicao() {
        return instituicao;
    }

    public void setInstituicao(Instituicao instituicao) {
        this.instituicao = instituicao;
    }

    @Override
    public Boolean validar(){
        return null;
    }
}
