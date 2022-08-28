package sptech.petfinderapimsg.services;

import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sptech.petfinderapimsg.entities.Demanda;
import sptech.petfinderapimsg.entities.Mensagem;
import sptech.petfinderapimsg.entities.Usuario;
import sptech.petfinderapimsg.repositories.DemandaRepository;
import sptech.petfinderapimsg.repositories.MensagemRepository;
import sptech.petfinderapimsg.repositories.UsuarioRepository;
import sptech.petfinderapimsg.req.DtoPostMessage;
import sptech.petfinderapimsg.res.DtoMessageResponse;
import sptech.petfinderapimsg.services.exceptions.EntityNotFoundException;
import sptech.petfinderapimsg.services.exceptions.IdNotFoundException;
import sptech.petfinderapimsg.services.exceptions.NoContentException;

@Service
public class MessageService {

    // dependency injection
    @Autowired
    private MensagemRepository messageRepository;

    @Autowired
    private DemandaRepository demandaRepository;

    @Autowired
    private UsuarioRepository usuarioRepositorio;

    // return list of messages by demanda id
    public List<DtoMessageResponse> getMessageListByDemandaId(Integer id) {

        // verifying if demanda exists
        if(demandaRepository.existsById(id)) {

            List<DtoMessageResponse> messages = messageRepository.findMessagesByDemandaId(id);

            // 200 ok
            if (messages.size() > 0) {
                return messages;
            }

            // 204 no content
            throw new NoContentException("Mensagens");
        }

        // 404 demanda not found
        throw new EntityNotFoundException(id);
    }

    // send message to demanda chat
    public DtoMessageResponse createMessage(@Valid DtoPostMessage dto) {
        Mensagem message = getMessageFromPostMessage(dto);
        message = messageRepository.save(message);
        return new DtoMessageResponse(message);
    }

    // fast way to create Message entity from DtoPostMessage
    private Mensagem getMessageFromPostMessage(DtoPostMessage dto) {

        // getting relatioships
        Optional<Demanda> demanda = demandaRepository.findById(dto.getDemandaId());
        Optional<Usuario> usuario = usuarioRepositorio.findById(dto.getRemetenteId());

        // validating 

        if (!demanda.isPresent()) {
            // 400 demanda does not exists
            throw new IdNotFoundException(dto.getDemandaId(), "demandaId");
        }

        if(!usuario.isPresent()) {
            // 400 remetenteId odes not exists
            throw new IdNotFoundException(dto.getRemetenteId(), "remetenteId");
        }

        // converting
        Mensagem mensagem = new Mensagem();
        mensagem.setConteudo(dto.getConteudo());
        mensagem.setDataEnvio(dto.getDataEnvio());
        mensagem.setTipo(dto.getTipo());
        mensagem.setDemanda(demanda.get());
        mensagem.setUsuario(usuario.get());
        return mensagem;
    }
}
