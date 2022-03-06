package com.sptech.estruturadedados.rules;

public class Usuario extends Autenticavel {

    private Endereco endereco;

    public Usuario(String nome, String email, String senha, Endereco endereco) {
        super(nome, email, senha);
        this.endereco = endereco;
    }

    public Usuario(String nome, String email, String senha) {
        super(nome, email, senha);
    }

    @Override
    public boolean autenticar(Autenticavel a) {
            return ControlaAutenticavel.autenticar(a);
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "endereco=" + endereco +
                "} " + super.toString();
    }

}
