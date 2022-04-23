package petfinder.petfinderapi.entidades;

import java.util.Objects;

public class Colaborador extends Pessoa {

    private int fkInstituicao;

    public Colaborador(String nome, String email, String senha, TodosCargo cargo, int instituicao) {
        super(nome, email, senha, cargo);
        this.fkInstituicao = instituicao;
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


    public int getFkInstituicao() {
        return fkInstituicao;
    }

    public void setFkInstituicao(int fkInstituicao) {
        this.fkInstituicao = fkInstituicao;
    }
}
