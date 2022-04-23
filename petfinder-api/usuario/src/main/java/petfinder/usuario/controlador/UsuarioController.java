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

    @GetMapping
    public ResponseEntity getUsuario() {
        List<Usuario> listaUsuario = usuarioRepository.findAll();
        if (listaUsuario.isEmpty()) {
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(listaUsuario);
    }

    @GetMapping("/{id}")
    public ResponseEntity getUsuarioById(@PathVariable int id) {
        List<Usuario> listaUsuario = usuarioRepository.findAllById(Collections.singleton(id));
        if (!usuarioRepository.existsById(id)) {
            return ResponseEntity.status(400).build();
        }
        return ResponseEntity.status(200).body(listaUsuario);
    }

    // revisar
    @PostMapping
    public ResponseEntity postUsuario(@RequestBody Usuario novoUsuario) {
        return ResponseEntity.status(200).body(usuarioRepository.save(novoUsuario));
    }

    @PutMapping("/{id}")
    public ResponseEntity updateUsuario(@PathVariable int id, @RequestBody Usuario novoUsuario) {
        if (usuarioRepository.existsById(id)) {
            usuarioRepository.save(novoUsuario);
            return ResponseEntity.status(200).build();
        }
        return ResponseEntity.status(404).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteUsuario(@PathVariable int id) {
        if (usuarioRepository.existsById(id)) {
            usuarioRepository.deleteById(id);
            return ResponseEntity.status(200).build();
        } else {
            return ResponseEntity.status(404).build();
        }
    }

    // não estava sinalizado o tipo de metodo na arquitetura, deve ter um atributo autenticado??
    @GetMapping("/autenticar")
    public ResponseEntity autenticar(@PathVariable String email, @PathVariable String senha) {
        List<Usuario> listaUsuario = usuarioRepository.findByEmailESenha(email, senha);
        if (listaUsuario.isEmpty()) {
            return ResponseEntity.status(404).build();
        }
        return ResponseEntity.status(200).body(listaUsuario);
    }

    @GetMapping("/acesso/{fkInstituicao}/{enumNivelAcesso}")
    public ResponseEntity getUsuarioByNivelAcesso(@PathVariable int fkInstituicao, @PathVariable EnumUsuarioNivelAcesso enumNivelAcesso) {
        List<Usuario> listaUsuario = usuarioRepository.findByNivelAcesso(fkInstituicao, enumNivelAcesso.name());
        if (listaUsuario.isEmpty()) {
            return ResponseEntity.status(404).build();
        }
        return ResponseEntity.status(200).body(listaUsuario);
    }

}
