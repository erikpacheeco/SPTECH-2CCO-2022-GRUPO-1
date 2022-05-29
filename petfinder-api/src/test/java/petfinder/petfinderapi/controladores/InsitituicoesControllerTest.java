package petfinder.petfinderapi.controladores;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import petfinder.petfinderapi.entidades.Endereco;
import petfinder.petfinderapi.entidades.Instituicao;
import petfinder.petfinderapi.entidades.Usuario;

@SpringBootTest
// @WebAppConfiguration
public class InsitituicoesControllerTest {

    @Autowired
    private InsitituicoesController controller;

    // methods
    private Endereco fastEndereco() {
        Endereco endereco = new Endereco();

        endereco.setBairro("bairro 1");
        endereco.setCep("12345678");
        endereco.setCidade("São Paulo");
        endereco.setNum("100");
        endereco.setRua("Rua do Haddock Lobbo");
        endereco.setUf("SP");

        return endereco;
    }
    private Instituicao fastInstituicao() {
        Instituicao instituicao = new Instituicao();

        instituicao.setEndereco(fastEndereco());
        instituicao.setNome("Instituicao 1");
        instituicao.setTelefone("11 91111-1111");
        instituicao.setEndereco(fastEndereco());

        return instituicao;
    } 
    private Usuario fastUsuario() {
        Usuario usuario = new Usuario();

        usuario.setEmail("usuario.temporario@email.com");
        usuario.setEndereco(null);
        usuario.setInstituicao(fastInstituicao());
        usuario.setLogado(false);
        usuario.setNivelAcesso("admin");
        usuario.setNome("usuario da silva");
        usuario.setSenha("urubu100");

        return usuario;
    }
    
    // post
    @Test
    @DisplayName("Retorna 201 e instituicao cadastrada no corpo se dados estiverem válidos")
    void testPostInstituicaoValida() {

        // criando corpo da requisição
        Usuario novaInstituicao = fastUsuario();
        ResponseEntity<Instituicao> res = controller.postInstituicao(novaInstituicao);

        // asserts
        assertEquals(201, res.getStatusCodeValue());
        assertEquals("Instituicao 1", res.getBody().getNome());
        assertEquals("São Paulo", res.getBody().getEndereco().getCidade()); 

        // deletando endereco, instituicao e usuário
        controller.deleteInstituicao(res.getBody().getId());
    }

    @Test
    @DisplayName("Deve retornar status 400 se nível de acesso inválido (diferente de admin)")
    void testPostInstituicaoInvalid() {

        // Usuario usuario = fastUsuario();
        Usuario usuario = fastUsuario();

        usuario.setNivelAcesso("user");

        ResponseEntity<Instituicao> res = controller.postInstituicao(usuario);
        
        assertEquals(400, res.getStatusCodeValue());
               
    }

    @Test
    @DisplayName("Retorna 200 e instituicao no corpo, id é válido")
    void getInstituicaoByIdValid() {
        
        // creating
        Usuario usuario = fastUsuario();
        int id = controller.postInstituicao(usuario).getBody().getId();

        ResponseEntity<Instituicao> res = controller.getInstituicaoById(id);
        
        // asserts
        assertEquals(200, res.getStatusCodeValue());
        assertEquals("Instituicao 1", res.getBody().getNome());
        assertEquals("Rua do Haddock Lobbo", res.getBody().getEndereco().getRua());

        // deleting
        controller.deleteInstituicao(res.getBody().getId());
    }

    @Test
    @DisplayName("Deve retornar 404 se id da instituicao não for válido")
    void testGetInstituicaoByIdInvalid() {
        // creating and deleting
        int id = controller.postInstituicao(fastUsuario()).getBody().getId();
        controller.deleteInstituicao(id);

        // request
        ResponseEntity<Instituicao> res = controller.getInstituicaoById(id);

        // asserts
        assertEquals(404, res.getStatusCodeValue());
        assertNull(res.getBody());
    }

    @Test
    void getListaDistanciasInstituicaoes() {

        ResponseEntity<List<Instituicao>> res = controller.getListaDistanciasInstituicaoes("05846140", 2);
        
        assertEquals(200, res.getStatusCodeValue());
        assertEquals(1, res.getBody().size());

        res = controller.getListaDistanciasInstituicaoes("05846140", 29);

        assertEquals(200, res.getStatusCodeValue());
        assertEquals(2, res.getBody().size());
    }
}
