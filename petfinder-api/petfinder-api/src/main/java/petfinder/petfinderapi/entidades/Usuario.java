package petfinder.petfinderapi.entidades;

import java.util.Objects;

public class Usuario extends Pessoa {

    public Usuario(String nome, String email, String senha, Endereco endereco) {
        super(nome, email, senha, endereco);
    }

    @Override
    public Boolean validar(){
        Boolean boolNome = Objects.isNull(super.getNome()) || super.getNome().isEmpty() || super.getNome().isBlank();
        Boolean boolEmail = Objects.isNull(super.getEmail()) || super.getEmail().isEmpty() || super.getEmail().isBlank();
        Boolean boolSenha = Objects.isNull(super.getSenha()) || super.getSenha().isEmpty() || super.getSenha().isBlank();
        Boolean boolEndereco = !super.getEndereco().validar();

        if(!(boolNome || boolEmail || boolSenha || boolEndereco)){
            return true;
        }
        return false;
    }
}
