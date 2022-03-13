package petfinder.petfinderapi.entidades;

import java.util.Objects;

public class Colaborador extends Pessoa {

    private Instituicao instituicao;

    public Colaborador(String nome, String email, String senha, TodosCargo cargo, Instituicao instituicao) {
        super(nome, email, senha, cargo);
        this.instituicao = instituicao;
    }

    //    MÉTODOS

    @Override
    public Boolean validar() {
        Boolean boolNome = Objects.isNull(super.getNome()) || super.getNome().isEmpty() || super.getNome().isBlank();
        Boolean boolEmail = Objects.isNull(super.getEmail()) || super.getEmail().isEmpty() || super.getEmail().isBlank();
        Boolean boolSenha = Objects.isNull(super.getSenha()) || super.getSenha().isEmpty() || super.getSenha().isBlank();
        Boolean boolCargo = Objects.isNull(super.getCargo());

        if(!(boolNome || boolEmail || boolSenha || boolCargo)){
            return true;
        }
        return false;
    }

    //    GETTERS E SETTERS

    public Instituicao getInstituicao() {
        return instituicao;
    }
    public void setInstituicao(Instituicao instituicao) {
        this.instituicao = instituicao;
    }

}
