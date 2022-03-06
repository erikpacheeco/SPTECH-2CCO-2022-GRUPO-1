package com.sptech.estruturadedados.rules;

import java.util.ArrayList;
import java.util.List;

public class ControlaAutenticavel {

    private static final List<Colaborador> colaboradores = new ArrayList<Colaborador>();
    private static final List<Usuario> usuarios = new ArrayList<Usuario>();


    public static boolean autenticar(Autenticavel a){
        if (a instanceof Colaborador) {
            for (Colaborador c : colaboradores) {
                if(c.getEmail().equals(a.getEmail()) && c.getSenha().equals(a.getSenha())) {
                    return true;
                }
            }
            return false;
        } else if (a instanceof Usuario) {
            for(Usuario u : usuarios) {
                if (u.getEmail().equals(a.getEmail()) && u.getSenha().equals(a.getSenha())) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    public static boolean addAutenticavel(Autenticavel a){
        if (a instanceof Colaborador) {
            Colaborador colaborador = (Colaborador) a;
            colaboradores.add(colaborador);
        } else if (a instanceof Usuario) {
            Usuario user = (Usuario) a;
            usuarios.add(user);
        }
        return false;
    }



}
