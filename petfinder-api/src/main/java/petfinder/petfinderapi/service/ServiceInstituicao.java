package petfinder.petfinderapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import petfinder.petfinderapi.entidades.Usuario;
import petfinder.petfinderapi.repositorios.UsuarioRepositorio;
import petfinder.petfinderapi.requisicao.DtoAdmRequest;
import petfinder.petfinderapi.service.exceptions.InvalidFieldException;

@Service
public class ServiceInstituicao {
    
    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    public Usuario createInstituicao(DtoAdmRequest dto) {

        // // 400 email já em uso
        if (!usuarioRepositorio.findByEmail(dto.convert().getEmail()).isEmpty()) {
            throw new InvalidFieldException("email", "o email " + dto.convert().getEmail() + " já está em uso!");
        }

        // 201 created
        return usuarioRepositorio.save(dto.convert());
    }  
}
