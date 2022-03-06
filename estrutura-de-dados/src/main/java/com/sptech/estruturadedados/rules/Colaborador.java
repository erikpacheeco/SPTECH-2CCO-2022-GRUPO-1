package com.sptech.estruturadedados.rules;

public class Colaborador extends Autenticavel{

    private String cargo;

    public Colaborador(String nome, String email, String senha, String cargo) {
        super(nome, email, senha);
        this.cargo = cargo;
    }

    @Override
    public boolean autenticar(Autenticavel a) {
        return false;
    }

    public void cadastrarPet(Pet pet){

    }

    public boolean finalizarAdocao(int index){
        return false;
    }

    public void editarPet(int i, Pet pet){

    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    @Override
    public String toString() {
        return "Colaborador{" +
                "cargo='" + cargo + '\'' +
                "} " + super.toString();
    }

}
