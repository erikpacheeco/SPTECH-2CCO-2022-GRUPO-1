package com.sptech.estruturadedados.rules;

public class Teste {
    public static void main(String[] args) {


        Colaborador colaborador = new Colaborador("Lucas", "lucas.mequista@bandtec.com.br", "password", "Dono da ong");
        Usuario usuario = new Usuario("Erik", "erik.pacheco@bantec.com.br", "senha");
        Pet pet = new Pet("Antônio", "Cachorro", "Shits-zu","Pequeno");
        Pet pet1 = new Pet("Jade", "Cachorro", "Shits-zu","Pequeno");

        System.out.println(usuario.autenticar(usuario));
        System.out.println(colaborador.autenticar(colaborador));

        ControlaAutenticavel.addAutenticavel(colaborador);
        ControlaAutenticavel.addAutenticavel(usuario);

        System.out.println(usuario.autenticar(usuario));
        System.out.println(colaborador.autenticar(colaborador));

        ControlaPet.addPet(pet);
        ControlaPet.addPet(pet1);

        System.out.println(ControlaPet.listarPet());

        ControlaPet.removerPet(1);

        colaborador.finalizarAdocao(1);

        ControlaPet.editarPet(0, new Pet("Antonio", "gato", "siames", "giga"));

        System.out.println(ControlaPet.listarPet());

    }
}
