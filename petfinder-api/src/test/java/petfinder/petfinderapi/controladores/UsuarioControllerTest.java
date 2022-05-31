package petfinder.petfinderapi.controladores;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;
import javax.validation.ConstraintViolationException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.ResponseEntity;

import petfinder.petfinderapi.entidades.Caracteristica;
import petfinder.petfinderapi.entidades.Endereco;
import petfinder.petfinderapi.entidades.Instituicao;
import petfinder.petfinderapi.entidades.Usuario;
import petfinder.petfinderapi.entidades.UsuarioHasInteresse;
import petfinder.petfinderapi.requisicao.CriacaoUsuario;
import petfinder.petfinderapi.requisicao.UsuarioLogin;
import petfinder.petfinderapi.resposta.UsuarioSemSenha;

@SpringBootTest
public class UsuarioControllerTest {

    @Autowired
    private UsuarioController controller;

    @Autowired
    private PetsController petController;

    @Autowired
    private InsitituicoesController instController;

    // methods
    private CriacaoUsuario fastUsuario() {

        Usuario usuario = new Usuario(); 

        usuario.setNome("user test");
        usuario.setEmail("user.test@test.com");
        usuario.setEndereco(fastEndereco());
        usuario.setInstituicao(null);
        usuario.setLogado(false);
        usuario.setNivelAcesso("USER");
        usuario.setSenha("urubu101");

        return new CriacaoUsuario(usuario);
    }
    private Endereco fastEndereco() {
        Endereco endereco = new Endereco();

        endereco.setCep("05845270");
        endereco.setUf("SP");
        endereco.setCidade("São Paulo");
        endereco.setBairro("Jardim Brasília");
        endereco.setRua("Rua Benedita Lima Teixeira");
        endereco.setNum("145");

        return endereco;
    }

    // private Instituicao fastInstituicao() {

    // }

    // tests
    
    @Test
    @DisplayName("Deve retornar status 200 e usuário no corpo quando ID é válido")
    void getUsuarioByIdValido() {

        // requests
        ResponseEntity<UsuarioSemSenha> created = controller.postUsuario(fastUsuario());
        ResponseEntity<UsuarioSemSenha> res = controller.getUsuarioById(created.getBody().getId());
        controller.deleteUsuario(res.getBody().getId());

        // asserts
        assertEquals(200, res.getStatusCodeValue());
        assertEquals("user.test@test.com", res.getBody().getEmail());
        assertEquals("São Paulo", res.getBody().getEndereco().getCidade());
    }

    @Test
    @DisplayName("Deve retornar status 404 e corpo vazio quando ID é inválido")
    void getUsuarioByIdInvalido() {
        // requests
        ResponseEntity<UsuarioSemSenha> created = controller.postUsuario(fastUsuario());
        controller.deleteUsuario(created.getBody().getId());
        ResponseEntity<UsuarioSemSenha> res = controller.getUsuarioById(created.getBody().getId());

        // assert
        assertEquals(404, res.getStatusCodeValue());
        assertNull(res.getBody());
    }

    @Test 
    @DisplayName("Deve retornar status 201 se usuário for válido")
    void postUsuarioValido() {

        // requests
        ResponseEntity<UsuarioSemSenha> created = controller.postUsuario(fastUsuario());
        controller.deleteUsuario(created.getBody().getId());

        // asserts
        assertEquals(201, created.getStatusCodeValue());
        assertEquals("user.test@test.com", created.getBody().getEmail());
        assertEquals("user test", created.getBody().getNome());
        assertEquals("USER", created.getBody().getNivelAcesso());
        assertEquals("05845270", created.getBody().getEndereco().getCep());
    }

    @Test
    @DisplayName("Deve retornar status 201 se usuário e seus interesses forem válidos")
    void postUsuarioEInteressesValidos() {

        List<Caracteristica> interessesPossiveis = petController.getCaracteristica().getBody();
        CriacaoUsuario usuario = fastUsuario();
        usuario.getInteresses().add(interessesPossiveis.get(0));
        usuario.getInteresses().add(interessesPossiveis.get(1));

        ResponseEntity<UsuarioSemSenha> created = controller.postUsuario(usuario);
        ResponseEntity<List<UsuarioHasInteresse>> userInteresses = controller.getUsuarioInteresse(created.getBody().getId());

        assertEquals(usuario.getInteresses().get(0).getCaracteristicas(), userInteresses.getBody().get(0).getFkCaracteristica().getCaracteristicas());
        assertEquals(usuario.getInteresses().get(1).getCaracteristicas(), userInteresses.getBody().get(1).getFkCaracteristica().getCaracteristicas());
        
        controller.deleteUsuario(created.getBody().getId());
    }

    @Test
    @DisplayName("Deve retornar status 409 se o email já estiver sendo utilizado")
    void postUsuarioEmailInvalido() {

        // requests
        ResponseEntity<UsuarioSemSenha> created = controller.postUsuario(fastUsuario());
        ResponseEntity<UsuarioSemSenha> conflict = controller.postUsuario(fastUsuario());
        controller.deleteUsuario(created.getBody().getId());

        // asserts
        assertEquals(409, conflict.getStatusCodeValue());
        assertNull(conflict.getBody());
    }

    @Test
    @DisplayName("Deve retornar status 400 se o nível de acesso for inválido")
    void postUsuarioNivelDeAcessoInvalido() {

        // requests
        Usuario usuario = fastUsuario().getUsuario();
        usuario.setNivelAcesso("invalidLevel");
        ResponseEntity<UsuarioSemSenha> res = controller.postUsuario(new CriacaoUsuario(usuario));

        // asserts
        assertEquals(400, res.getStatusCodeValue());
        assertNull(res.getBody());
    }

    @Test
    @DisplayName("Deve retornar ConstraintViolationException se valores inconsistentes com o banco forem inseridos")
    void postUsuarioCamposNulos() {

        assertThrows(ConstraintViolationException.class, () -> {
            CriacaoUsuario usuario = fastUsuario();
            usuario.getUsuario().setNome(null);
            controller.postUsuario(usuario);
        });

        assertThrows(ConstraintViolationException.class, () -> {
            CriacaoUsuario usuario = fastUsuario();
            usuario.getUsuario().setEmail(null);
            controller.postUsuario(usuario);
        });

        assertThrows(ConstraintViolationException.class, () -> {
            CriacaoUsuario usuario = fastUsuario();
            usuario.getUsuario().setEmail(null);
            controller.postUsuario(usuario);
        });

    }

    @Test 
    @DisplayName("Deve retornar status 200 se usuário for válido")
    void updateUsuarioValido() {

        // creating
        Usuario usuario = fastUsuario().getUsuario();
        ResponseEntity<UsuarioSemSenha> created = controller.postUsuario(new CriacaoUsuario(usuario));
        usuario.setNome("teste update");
        ResponseEntity<UsuarioSemSenha> updated = controller.updateUsuario(created.getBody().getId(), usuario);
        controller.deleteUsuario(created.getBody().getId());

        // assert
        assertEquals(200, updated.getStatusCodeValue());
        assertEquals("teste update", updated.getBody().getNome());
    }

    @Test
    @DisplayName("Deve retornar status 409 se o email já estiver sendo utilizado")
    void putUsuarioEmailInvalido() {
        // creating
        Usuario usuario1 = fastUsuario().getUsuario();
        usuario1.setEmail("user.test1@test.com");
        ResponseEntity<UsuarioSemSenha> created1 = controller.postUsuario(new CriacaoUsuario(usuario1));

        Usuario usuario2 = fastUsuario().getUsuario();
        usuario2.setEmail("user.test2@test.com");
        ResponseEntity<UsuarioSemSenha> created2 = controller.postUsuario(new CriacaoUsuario(usuario2));
        
        ResponseEntity<UsuarioSemSenha> updated1 = controller.updateUsuario(created1.getBody().getId(), usuario2);
        controller.deleteUsuario(created1.getBody().getId());
        controller.deleteUsuario(created2.getBody().getId());

        // asserts
        assertEquals(409, updated1.getStatusCodeValue());
    }

    @Test
    @DisplayName("Deve retornar status 400 se o nível de acesso for inválido")
    void putUsuarioNivelDeAcessoInvalido() {

        // creating
        CriacaoUsuario usuario = fastUsuario();
        int id = controller.postUsuario(usuario).getBody().getId();
        usuario.getUsuario().setNivelAcesso("invalido");
        ResponseEntity<UsuarioSemSenha> res = controller.updateUsuario(id, usuario.getUsuario());

        controller.deleteUsuario(id);

        // asserts
        assertEquals(400, res.getStatusCodeValue());
    }

    @Test
    @DisplayName("Deve retornar TransactionSystemException se valores inconsistentes com o banco forem inseridos")
    void putUsuarioCamposNulos() {

        // created
        Usuario usuario = fastUsuario().getUsuario();
        int id = controller.postUsuario(new CriacaoUsuario(usuario)).getBody().getId();

        // asserts
        assertThrows(InvalidDataAccessApiUsageException.class, () -> {
            Usuario tempUsuario = fastUsuario().getUsuario();
            tempUsuario.setEmail(null);
            controller.updateUsuario(id, tempUsuario);
        });

        assertThrows(InvalidDataAccessApiUsageException.class, () -> {
            Usuario tempUsuario = fastUsuario().getUsuario();
            tempUsuario.setNome(null);
            controller.updateUsuario(id, tempUsuario);
        });

        controller.deleteUsuario(id);
    }

    @Test
    @DisplayName("Deve retornar status 404 se id do usuário não for encontrado")
    void putUsuarioUsuarioNaoEncontrado() {
        
        // creating
        int id = controller.postUsuario(fastUsuario()).getBody().getId();
        controller.deleteUsuario(id);
        
        // request
        ResponseEntity<UsuarioSemSenha> res = controller.updateUsuario(id, fastUsuario().getUsuario());
        
        // asserts
        assertEquals(404, res.getStatusCodeValue());
        assertNull(res.getBody());
    }

    @Test
    @DisplayName("Deve retornar status 200 se a autenticação for bem sucedida ")
    void postAutenticacaoBemSucedida() {

        // created
        controller.postUsuario(fastUsuario());
        ResponseEntity<UsuarioSemSenha> res = controller.login(new UsuarioLogin("user.test@test.com", "urubu101"));
        controller.deleteUsuario(res.getBody().getId());

        // asserts
        assertEquals(200, res.getStatusCodeValue());
        assertInstanceOf(Integer.class, res.getBody());

    }

    @Test
    @DisplayName("Deve retornar status 401 se a autenticação for mal sucedida ")
    void postAutenticacaoEmailErrado() {

        // creating
        // creating
        ResponseEntity<UsuarioSemSenha> created = controller.postUsuario(fastUsuario());

        // requesting
        UsuarioLogin login1 = new UsuarioLogin("user.test2@test.com", "urubu101");
        ResponseEntity<UsuarioSemSenha> res1 = controller.login(login1);

        UsuarioLogin login2 = new UsuarioLogin("user.test@test.com", "urubu102");
        ResponseEntity<UsuarioSemSenha> res2 = controller.login(login2);

        // deleting
        controller.deleteLogoff(created.getBody().getId());

        // asserts
        assertEquals(401, res1.getStatusCodeValue());
        assertNull(res1.getBody());
        assertEquals(401, res2.getStatusCodeValue());
        assertNull(res2.getBody());
    }

    @Test
    @DisplayName("Retorna status 200 e lista de interesses do usuário")
    void getUsuarioInteresse() {

        // creating
        List<Caracteristica> interesses = petController.getCaracteristica().getBody();
        CriacaoUsuario usuario = fastUsuario();
        usuario.getInteresses().add(interesses.get(0));
        usuario.getInteresses().add(interesses.get(1));

        // requesting
        ResponseEntity<UsuarioSemSenha> createdUser = controller.postUsuario(usuario);
        ResponseEntity<List<UsuarioHasInteresse>> res = controller.getUsuarioInteresse(createdUser.getBody().getId());

        // deleting
        controller.deleteUsuario(createdUser.getBody().getId());

        // asserts
        assertEquals(200, res.getStatusCodeValue());
        assertEquals("Fofo", res.getBody().get(0).getFkCaracteristica().getCaracteristicas());
        assertEquals("Cheiroso", res.getBody().get(1).getFkCaracteristica().getCaracteristicas());
    }

    @Test
    @DisplayName("Retorna status 204 se usuário não tiver interesses")
    void getUsuarioInteresseNoContent() {

        // creating
        CriacaoUsuario usuario = fastUsuario();
        UsuarioSemSenha created = controller.postUsuario(usuario).getBody();

        // requesting
        ResponseEntity<List<UsuarioHasInteresse>> res = controller.getUsuarioInteresse(created.getId());
            
        // deleting
        controller.deleteUsuario(created.getId());

        // asserts
        assertEquals(204, res.getStatusCodeValue());
        assertNull(res.getBody());
    }

    @Test
    @DisplayName("Retorna status 404 se usuário não existir")
    void getUsuarioInteresseNotFound() {
        
        int id = controller.postUsuario(fastUsuario()).getBody().getId();
        controller.deleteUsuario(id);

        ResponseEntity<List<UsuarioHasInteresse>> res = controller.getUsuarioInteresse(id);

        assertEquals(404, res.getStatusCodeValue());
        assertNull(res.getBody());
    }
}
