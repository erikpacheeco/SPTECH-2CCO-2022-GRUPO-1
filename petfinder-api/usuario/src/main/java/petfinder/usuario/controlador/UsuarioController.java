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
import petfinder.usuario.requisicao.UsuarioLogin;

import javax.validation.Valid;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

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

    @PostMapping
    public ResponseEntity postUsuario(@RequestBody @Valid Usuario novoUsuario) {
        if (Objects.isNull(novoUsuario)) {
            return ResponseEntity.status(400).build();
        }
        return ResponseEntity.status(201).body(usuarioRepository.save(novoUsuario));
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

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid UsuarioLogin usuarioLogin) {
        List<Usuario> listaUsuario = usuarioRepository.findByEmailESenha(usuarioLogin.getEmail(), usuarioLogin.getSenha());
        if (listaUsuario.isEmpty()) {
            return ResponseEntity.status(401).build();
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
