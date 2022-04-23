package petfinder.usuario.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import petfinder.usuario.entidade.EnumUsuarioNivelAcesso;
import petfinder.usuario.entidade.Usuario;
import petfinder.usuario.repositorio.CaracteristicaRepository;
import petfinder.usuario.repositorio.EnderecoRepository;
import petfinder.usuario.repositorio.UsuarioHasInteresseRepository;
import petfinder.usuario.repositorio.UsuarioRepository;

import javax.persistence.Id;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private CaracteristicaRepository caracteristicaRepository;

    @Autowired
    private UsuarioHasInteresseRepository usuarioHasInteresseRepository;

    // falta caso não haja usuario cadastrado
    @GetMapping
    public ResponseEntity getUsuario() {
        List<Usuario> listaUsuario = usuarioRepository.findAll();
        return ResponseEntity.status(200).body(listaUsuario);
    }

    // falta caso não ache o usuario
    @GetMapping("/{id}")
    public ResponseEntity getUsuarioById(@PathVariable int id) {
        List<Usuario> listaUsuario = usuarioRepository.findAllById(Collections.singleton(id));
        return ResponseEntity.status(200).body(listaUsuario);
    }

    // revisar
    @PostMapping
    public ResponseEntity postUsuario(@RequestBody Usuario novoUsuario) {
        return ResponseEntity.status(200).body(usuarioRepository.save(novoUsuario));
    }

    @PutMapping("/{id}")
    public ResponseEntity updateUsuario(@PathVariable int id, @RequestBody Usuario novoUsuario) {
        return null;
    }

    // falta caso não ache o id
    @DeleteMapping("/{id}")
    public ResponseEntity deleteUsuario(@PathVariable int id) {
        usuarioRepository.deleteById(id);
        return ResponseEntity.status(200).build();
    }

    @PostMapping("/autenticar")
    public ResponseEntity autenticar(@PathVariable String email, @PathVariable String senha) {
        //List<Usuario> listaUsuario = usuarioRepository.findByEmailAndSenha(email, senha);
        //return ResponseEntity.status(200).body(listaUsuario);
        return null;
    }

    @GetMapping("/acesso")
    public ResponseEntity getUsuarioByNivelAcesso(@PathVariable int Instituicao, @PathVariable EnumUsuarioNivelAcesso enumNivelAcesso) {
        return null;
    }

}
