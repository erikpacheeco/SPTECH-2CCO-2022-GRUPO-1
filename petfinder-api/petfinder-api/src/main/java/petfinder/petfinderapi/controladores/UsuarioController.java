package petfinder.petfinderapi.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import petfinder.petfinderapi.entidades.Usuario;
import petfinder.petfinderapi.repositorios.CaracteristicaRepositorio;
import petfinder.petfinderapi.repositorios.EnderecoRepositorio;
import petfinder.petfinderapi.repositorios.UsuarioHasInteresseRepositorio;
import petfinder.petfinderapi.repositorios.UsuarioRepositorio;
import petfinder.petfinderapi.requisicao.UsuarioLogin;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.validation.Valid;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    // repositorios
    @Autowired
    private UsuarioRepositorio usuarioRepository;

    @Autowired
    private EnderecoRepositorio enderecoRepository;

    @Autowired
    private CaracteristicaRepositorio caracteristicaRepository;

    @Autowired
    private UsuarioHasInteresseRepositorio usuarioHasInteresseRepository;

    // endpoints
    @GetMapping
    public ResponseEntity<List<Usuario>> getUsuario() {
        List<Usuario> listaUsuario = usuarioRepository.findAll();
        if (listaUsuario.isEmpty()) {
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(listaUsuario);
    }

    // ======================================================
    // RETORNO INAPROPRIADO
    // Esperado:    status 404 (Vazio)
    // Atual:       status 400 (Vazio)
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Usuario>> getUsuarioById(@PathVariable int id) {
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        if (!usuarioRepository.existsById(id)) {
            return ResponseEntity.status(400).build();
        }
        return ResponseEntity.status(200).body(usuario);
    }

    // =====================================================
    // RETORNO INAPROPRIADO
    // Esperado:    status 201 (VAZIO)
    // Atual:       status 201 (Usuario)
    @PostMapping
    public ResponseEntity<Usuario> postUsuario(@RequestBody @Valid Usuario novoUsuario) {
        if (Objects.isNull(novoUsuario)) {
            return ResponseEntity.status(400).build();
        }
        return ResponseEntity.status(201).body(usuarioRepository.save(novoUsuario));
    }

    // ==============================================================================
    // Ao invés de atualizar dados de um usuário, está cadastrando um novo usuário
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateUsuario(@PathVariable int id, @RequestBody @Valid Usuario novoUsuario) {
        if (usuarioRepository.existsById(id)) {
            usuarioRepository.save(novoUsuario);
            return ResponseEntity.status(200).build();
        }
        return ResponseEntity.status(404).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteUsuario(@PathVariable int id) {
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

    @GetMapping("/acesso/{fkInstituicao}/{nivelAcesso}")
    public ResponseEntity<List<Usuario>> getUsuarioByNivelAcesso(@PathVariable int fkInstituicao, @PathVariable String nivelAcesso) {
        List<Usuario> listaUsuario = usuarioRepository.findByNivelAcesso(fkInstituicao, nivelAcesso);
        if (listaUsuario.isEmpty()) {
            return ResponseEntity.status(404).build();
        }
        return ResponseEntity.status(200).body(listaUsuario);
    }
}
