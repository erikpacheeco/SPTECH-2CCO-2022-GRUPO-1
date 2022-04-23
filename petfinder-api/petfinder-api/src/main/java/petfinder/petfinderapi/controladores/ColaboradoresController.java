package petfinder.petfinderapi.controladores;

import org.springframework.web.bind.annotation.*;
import petfinder.petfinderapi.entidades.Colaborador;
import petfinder.petfinderapi.entidades.Pet;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/colaboradores")
public class ColaboradoresController {

    public static List<Colaborador> colaboradores = new ArrayList<>();

    @GetMapping
    public List<Colaborador> listarColaboradores(){
        return colaboradores;
    }

    @PostMapping
    public String addColaborador(@RequestBody Colaborador novoColaborador){
        if(novoColaborador.validar()){
            colaboradores.add(novoColaborador);
            return "Novo Colaborador cadastrado com sucesso";
        }
        return "Dados Incorretos, por favor verifique e tente novamente";
    }

    @PostMapping("/{indiceColab}")
    public String addPet(@RequestBody Pet novoPet, @PathVariable int indiceColab){
        if(novoPet.validar()){
            novoPet.setFkInstituicao(colaboradores.get(indiceColab).getFkInstituicao());
            PetsController.pets.add(novoPet);
            return "Pet cadastrado com sucesso!";
        }
        return "Dados Incorretos, por favor verifique e tente novamente";
    }

    @DeleteMapping("/{indicePet}/{indiceColab}")
    public String validarAdocao(@PathVariable int indicePet,@PathVariable int indiceColab){
        if(!(PetsController.pets.size() <= indicePet)){
            if(!(colaboradores.size() <= indiceColab)){
                if(PetsController.pets.get(indicePet).getFkInstituicao() == colaboradores.get(indiceColab).getFkInstituicao()){
                    if (PetsController.pets.get(indicePet).isEmAdocao().equals(true)) {
                        PetsController.pets.remove(indicePet);
                        return "Pet adotado com sucesso, suas informações foram alteradas e tirados da lista";
                    } else {
                        return "O pet ainda está disponível para adoção";
                    }
                }
                return "O colaborador não é da mesma instituição que o pet";
            }
            return "Código do Colaborador não encontrado!";
        }
        return "Código do Pet não encontrado!";
    }

    @PutMapping("/{indicePet}/{indiceColab}")
    public String editarPet(@PathVariable int indicePet,@PathVariable int indiceColab,@RequestBody Pet petAtualizado)
    {
        if(!(PetsController.pets.size() <= indicePet)){
            if(!(colaboradores.size() <= indiceColab)){
                if(colaboradores.get(indiceColab).getFkInstituicao() == (PetsController.pets.get(indicePet).getFkInstituicao())){
                    petAtualizado.setFkInstituicao((PetsController.pets.get(indicePet).getFkInstituicao()));
                    petAtualizado.setEstadoAdocao(PetsController.pets.get(indicePet).isEmAdocao());
                    PetsController.pets.set(indicePet, petAtualizado);
                    return "O pet foi atualizado com sucesso!";
                }
                return "Colaborador não tem acesso a edição desse pet";
            }
            return "Código do Colaborador não encontrado";
        }
        return "Código do Pet não encontrado!";
    }

    @PostMapping("/in")
    public String login(@RequestBody Colaborador colaboradorCheck){
        for(Colaborador colab : colaboradores){
            if(colab.autenticarLogin(colaboradorCheck.getEmail(),colaboradorCheck.getSenha())){
                if(!colab.isLogado()){
                    colab.setLogado(true);
                    return "Colaborador logado com sucesso";
                }
                return "Colaborador já está logado";
            }
        }
        return "Email e/ou senha incorretos, por favor verifique e tente novamente";
    }

    @PostMapping("/off/{indice}")
    public String logoff(@PathVariable int indice){
        if(!(colaboradores.size() <= indice)){
            if(colaboradores.get(indice).isLogado()){
                return "Colaborador deslogado com sucesso";
            }
            return "Colaborador não está logado";
        }
        return "Codigo do Colaborador não encontrado";
    }

}
