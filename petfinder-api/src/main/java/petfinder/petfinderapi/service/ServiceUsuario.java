package petfinder.petfinderapi.service;

import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import petfinder.petfinderapi.entidades.Instituicao;
import petfinder.petfinderapi.entidades.Usuario;
import petfinder.petfinderapi.repositorios.InstituicaoRepositorio;
import petfinder.petfinderapi.repositorios.UsuarioRepositorio;
import petfinder.petfinderapi.requisicao.DtoColaboradorRequest;
import petfinder.petfinderapi.requisicao.DtoColaboradorSelfRequest;
import petfinder.petfinderapi.requisicao.DtoSysadmRequest;
import petfinder.petfinderapi.resposta.ColaboradorSimples;
import petfinder.petfinderapi.resposta.SysadmSimples;
import petfinder.petfinderapi.resposta.UsuarioSemSenha;
import petfinder.petfinderapi.service.exceptions.ConflictValueException;
import petfinder.petfinderapi.service.exceptions.EntityNotFoundException;
import petfinder.petfinderapi.service.exceptions.InvalidFieldException;
import petfinder.petfinderapi.service.exceptions.NoContentException;

@Service
public class ServiceUsuario {

    @Autowired
    private InstituicaoRepositorio repositoryInstituicao;

    @Autowired
    private UsuarioRepositorio repositoryUser;

    private final List<String> nivelAcesso = List.of("adm", "chatops"); 

    public ColaboradorSimples postColaborador(DtoColaboradorRequest dto) {

        // 404 not found
        Instituicao instituicao = repositoryInstituicao.findById(dto.getInstituicaoId()).orElseThrow(
            () -> {
                throw new EntityNotFoundException(dto.getInstituicaoId());
            }
        );

        // 201
        Usuario usuario = verifyColabFields(dto.convert());
        usuario.setInstituicao(instituicao);
        return new ColaboradorSimples(repositoryUser.save(usuario));
    }

    public SysadmSimples postSysadm(DtoSysadmRequest dto) {

        // 409 conflict
        if(repositoryUser.findByEmail(dto.getEmail()).size() > 0) {
            throw new ConflictValueException("email", dto.getEmail());
        }

        // 201
        Usuario usuario = dto.convert();
        return new SysadmSimples(repositoryUser.save(usuario));
    }

    // retorna colaborador baseado no id da instituicao
    public List<ColaboradorSimples> getColaboradorByInstituicaoId(int id, String categoria) {

        // verificando se instituicao existe
        if (repositoryInstituicao.existsById(id)) {

            // filter by access level
            if (Objects.nonNull(categoria)) {
                List<ColaboradorSimples> colabList = repositoryUser.findColaboradorByInstituicaoAndCategoria(id, categoria);

                // verificando se valor é nulo
                if (colabList.size() > 0) {
                    // 200 ok
                    return colabList;
                }
                // 204 no content
                throw new NoContentException("usuario");
            }

            // not fultered by access level
            List<ColaboradorSimples> colabList = repositoryUser.findColaboradorById(id);

            // verificando se valor é nulo
            if (colabList.size() > 0) {
                // 200 ok
                return colabList;
            }
            // 204 no content
            throw new NoContentException("usuario");
        }

        // 404 not found
        throw new EntityNotFoundException(id);
    }

    // return a list of users who are sub
    public List<UsuarioSemSenha> getPadrinhos(Integer id) {

        if(repositoryInstituicao.existsById(id)) {
            List<UsuarioSemSenha> padrinhos = repositoryUser.getPadrinhos(id);

            if (padrinhos.size() > 0) {
                return padrinhos;
            }

            // 204 no content
            throw new NoContentException("padrinhos");
        }

        // 404 not found
        throw new EntityNotFoundException(id);
    }

    // return list of users based on nivelAcesso
    public List<UsuarioSemSenha> getUsuarioByNivelAcesso(String nivelAcesso) {

        List<UsuarioSemSenha> users = repositoryUser.findUsuarioByNivelAcesso(nivelAcesso);

        // 200 ok
        if (users.size() > 0) {
            return users;
        }

        // 204 no content
        throw new NoContentException("usuario");
    }

    public UsuarioSemSenha putUsuario(int id, DtoColaboradorSelfRequest dto) {

        // 404 not found
        Usuario usuario = repositoryUser.findById(id).orElseThrow(
            () -> {
                throw new EntityNotFoundException(id);
            }
        );
        
        // updating values
        usuario.setEmail(dto.getEmail());
        usuario.setNome(dto.getNome());
        usuario.setSenha(dto.getSenha());
        
        // 400 bad request / 409 conflict
        verifyColabFields(usuario);
        
        // 200 ok
        return new UsuarioSemSenha(repositoryUser.save(usuario));
    }

    private Usuario verifyColabFields(Usuario entity) {
        // 400 bad request
        if (!nivelAcesso.contains(entity.getNivelAcesso())) {
            throw new InvalidFieldException("cargo", "Cargo inválido para o valor '" + entity.getNivelAcesso() + "'.");
        }

        // 409 conflict
        if(repositoryUser.findByEmail(entity.getEmail()).size() > 0) {
            throw new ConflictValueException("email", entity.getEmail());
        }

        return entity;
    }
}