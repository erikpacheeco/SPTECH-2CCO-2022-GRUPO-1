package petfinder.petfinderapi.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import petfinder.petfinderapi.entidades.Instituicao;
import petfinder.petfinderapi.repositorios.InstituicaoRepositorio;
import petfinder.petfinderapi.repositorios.UsuarioRepositorio;
import petfinder.petfinderapi.resposta.ColaboradorSimples;
import petfinder.petfinderapi.resposta.UsuarioSemSenha;
import petfinder.petfinderapi.service.exceptions.EntityNotFoundException;
import petfinder.petfinderapi.service.exceptions.NoContentException;

@Service
public class ServiceUsuario {

    @Autowired
    private InstituicaoRepositorio repositoryInstituicao;

    @Autowired
    private UsuarioRepositorio repositoryUser;

    // retorna colaborador baseado no id da instituicao
    public List<ColaboradorSimples> getColaboradorByInstituicaoId(int id) {

        List<ColaboradorSimples> colabList = repositoryUser.findColaboradorById(id);

        // verificando se valor é nulo
        if (colabList.size() > 0) {

            // 200 ok
            return colabList;
        }

        // verificando se instituicao existe
        if (repositoryInstituicao.existsById(id)) {
            // 204 no content
            throw new NoContentException("Instituição");
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
    
}
