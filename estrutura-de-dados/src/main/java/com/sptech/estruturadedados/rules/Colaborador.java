package com.sptech.estruturadedados.rules;

public class Colaborador extends Autenticavel{

    private String cargo;

    public Colaborador(String nome, String email, String senha, String cargo) {
        super(nome, email, senha);
        this.cargo = cargo;
    }

    @Override
    public boolean autenticar(Autenticavel a) {
      return ControlaAutenticavel.autenticar(a);
    }

    public static void cadastrarPet(Pet pet){
        ControlaPet.addPet(pet);
    }

    public static boolean finalizarAdocao(int index){
       return ControlaPet.removerPet(index);
    }

    public void editarPet(int i, Pet pet){
        ControlaPet.editarPet(i, pet);
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
