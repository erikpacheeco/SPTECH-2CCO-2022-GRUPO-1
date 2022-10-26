package petfinder.petfinderapi.utilitarios.HashTable;

import java.util.ArrayList;
import java.util.List;
import petfinder.petfinderapi.entidades.Pet;
import petfinder.petfinderapi.resposta.PetPerfil;

public class HashTable {
    
    // attribute
    private List<PetsInstituicao> tab;

    public HashTable(List<Pet> list) {

        tab = new ArrayList<PetsInstituicao>();

        while(!list.isEmpty()) {
            PetsInstituicao partition = new PetsInstituicao(list.get(0).getInstituicao().getId());

            for(int i = 0; i < list.size(); i++) {
                if(list.get(i).getInstituicao().getId() == partition.getId()) {
                    partition.add(new PetPerfil(list.remove(i)));
                }
            }

            tab.add(partition);
        }
    }

    public List<PetsInstituicao> getTab() {
        return tab;
    }

    public PetsInstituicao find(int id) {
        for (PetsInstituicao p : tab) {
            if (p.getId() == id) {
                return p;
            }
        }

        return null;
    }
}
