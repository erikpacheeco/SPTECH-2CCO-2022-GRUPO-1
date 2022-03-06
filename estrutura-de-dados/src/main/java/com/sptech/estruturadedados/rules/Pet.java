package com.sptech.estruturadedados.rules;

public class Pet {

    private String nome;
    private String especie;
    private String raca;
    private String porte;

    public Pet(String nome, String especie, String raca, String porte) {
        this.nome = nome;
        this.especie = especie;
        this.raca = raca;
        this.porte = porte;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public String getRaca() {
        return raca;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }

    public String getPorte() {
        return porte;
    }

    public void setPorte(String porte) {
        this.porte = porte;
    }

    @Override
    public String toString() {
        return "Pet{" +
                "nome='" + nome + '\'' +
                ", especie='" + especie + '\'' +
                ", raca='" + raca + '\'' +
                ", porte='" + porte + '\'' +
                '}';
    }

}
