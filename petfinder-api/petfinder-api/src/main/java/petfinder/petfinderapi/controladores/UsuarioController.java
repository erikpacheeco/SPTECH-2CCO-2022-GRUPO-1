package petfinder.petfinderapi.controladores;

import org.springframework.web.bind.annotation.*;
import petfinder.petfinderapi.entidades.Colaborador;
import petfinder.petfinderapi.entidades.Pet;
import petfinder.petfinderapi.entidades.Usuario;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    List<Usuario> usuarios = new ArrayList<>();

    @PostMapping
    public String addUsuario(@RequestBody Usuario novoUsuario){
        if(novoUsuario.validar()){
            usuarios.add(novoUsuario);
            return "Novo Usuario cadastrado(a) com sucesso!";
        }
        return "Dados Incorretos, por favor verifique e tente novamente";
    }

    @GetMapping("/p/{porte}")
    public List<Pet> procurarPorPorte(@PathVariable String porte){
        List<Pet> petsPorte = new ArrayList<>();
        for(Pet pet : PetsController.pets){
            if(pet.getPorte().equalsIgnoreCase(porte)){
                petsPorte.add(pet);
            }
        }
        return petsPorte;
    }

    @GetMapping("/r/{raca}")
    public List<Pet> procurarPorRaca(@PathVariable String raca){
        List<Pet> petsRaca = new ArrayList<>();
        for(Pet pet : PetsController.pets){
            if(pet.getRaca().equalsIgnoreCase(raca)){
                petsRaca.add(pet);
            }
        }
        return petsRaca;
    }

    @GetMapping("/t/{tipo}")
    public List<Pet> procurarPorTipo(@PathVariable String tipo){
        List<Pet> petsTipo = new ArrayList<>();
        for(Pet pet : PetsController.pets){
            if(pet.getTipo().equalsIgnoreCase(tipo)){
                petsTipo.add(pet);
            }
        }
        return petsTipo;
    }

    @GetMapping("/s/{sexo}")
    public List<Pet> procurarPorSexo(@PathVariable String sexo){
        List<Pet> petsSexo = new ArrayList<>();
        for(Pet pet : PetsController.pets){
            if(pet.getSexo().equalsIgnoreCase(sexo)){
                petsSexo.add(pet);
            }
        }
        return petsSexo;
    }

    @PutMapping("/{indice}")
    public String solicitarAdocao(@PathVariable int indice){
        if(!(PetsController.pets.size() <= indice)){
            Pet petDesejado = PetsController.pets.get(indice);
            petDesejado.setEstadoAdocao(true);
            return "O Pet agora está em processo de adoção, aguarde o retorno da instituição";
        }
        return "Pet não encontrado";
    }

    @PostMapping("/in")
    public String login(@RequestBody Usuario usuarioCheck){
        for(Usuario user : usuarios){
            if(user.autenticarLogin(usuarioCheck.getEmail(),usuarioCheck.getSenha())){
                if(user.isLogado()){
                    return "Usuario já está logado";
                }else{
                    user.setLogado(true);
                    return "Usuario logado com sucesso";
                }
            }
        }
        return "Email e/ou senha incorretos, por favor verifique e tente novamente";
    }

    @PostMapping("/off/{indice}")
    public String logoff(@PathVariable int indice){
        if(!(usuarios.size() <= indice)){
            if(usuarios.get(indice).isLogado()){
                return "Usuario deslogado com sucesso";
            }
            return "Usuario não está logado";
        }
        return "Codigo do Usuário não encontrado";
    }

    @GetMapping
    public List<Usuario> listarUsuarios(){
        return usuarios;
    }
}
