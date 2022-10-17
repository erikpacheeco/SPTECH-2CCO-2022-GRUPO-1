package petfinder.petfinderapi.utilitarios.HashTable;

import java.util.List;

import petfinder.petfinderapi.entidades.Pet;

public class HashTable {
    
    // attribute
    private List<PetsInstituicao> tab;

    public HashTable(List<Pet> list) {

        System.out.println("===========================================");

        while(!list.isEmpty()) {

            int id = list.get(0).getInstituicao().getId();
            System.out.println("id instituicao: " + id);
            PetsInstituicao partition = new PetsInstituicao(id);

            for(int i = 0; i <= list.size(); i++) {

                if (list.get(i) == null) {
                    continue;
                }
                
                if (list.get(i).getInstituicao().getId() == id) {

                    System.out.println("id pet: " + list.get(i).getId());

                    partition.add(list.remove(i));
                    continue;
                }

                tab.add(partition);
            }
        }

        System.out.println("===========================================");
    }

    public void display() {

    }

    public List<PetsInstituicao> getTab() {
        return tab;
    }
}
