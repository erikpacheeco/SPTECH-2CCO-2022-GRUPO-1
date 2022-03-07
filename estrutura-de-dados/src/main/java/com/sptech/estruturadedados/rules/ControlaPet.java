package com.sptech.estruturadedados.rules;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ControlaPet {

   private static final List<Pet> listaDePet = new ArrayList<Pet>();

    public static boolean addPet(Pet pet){
        if(Objects.nonNull(pet)) {
            listaDePet.add(pet);
            return true;
        }
        return false;
    }

    public static boolean removerPet(int i){
        if (i < listaDePet.size() && i >= 0) {
            listaDePet.remove(i);
            return true;
        }
        return false;
    }

    public static void editarPet(int i, Pet pet){
        if (i < listaDePet.size() && i >= 0) {
            listaDePet.get(i).setNome(pet.getNome());
            listaDePet.get(i).setEspecie(pet.getEspecie());
            listaDePet.get(i).setPorte(pet.getPorte());
            listaDePet.get(i).setRaca(pet.getRaca());
        }
    }

    public static List<Pet> listarPet() {
        return listaDePet;
    }
}
