package sptech.petfinderapimsg.controllers;

import static org.springframework.http.ResponseEntity.*;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import sptech.petfinderapimsg.controllers.util.HeaderConfig;
import sptech.petfinderapimsg.req.DtoPostMessage;
import sptech.petfinderapimsg.res.DtoMessageResponse;
import sptech.petfinderapimsg.services.MessageService;

@RestController
@RequestMapping("/message")
@CrossOrigin
public class MessageController {
    
    // dependency injection
    @Autowired
    private MessageService service;

    // endpoints

    @GetMapping("/{demandaId}")
    @Operation(description = "Endpoint que lista mensagens de uma demanda")
    @ApiResponse(responseCode = "201", description = "Created")
    @ApiResponse(responseCode = "204", description = "No Content", content = @Content)
    public ResponseEntity<List<DtoMessageResponse>> listMessages(@PathVariable Integer demandaId) {
        return ok(service.getMessageListByDemandaId(demandaId));
    }

    @PostMapping
    @Operation(description = "Endpoint que envia e retorna uma mensagem para o chat de uma demanda")
    @ApiResponse(responseCode = "201", description = "Created")
    @ApiResponse(responseCode = "404", description = "Not Found", content = @Content)
    @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content)
    public ResponseEntity<DtoMessageResponse> postMessage(@RequestBody @Valid DtoPostMessage dto) {
        DtoMessageResponse created = service.createMessage(dto);
        return created(HeaderConfig.getLocation(created.getId())).body(created);
    }

    @PostMapping("/file")
    @Operation(description = "Endpoint que envia e retorna uma mensagem para o chat de uma demanda")
    @ApiResponse(responseCode = "201", description = "Created")
    @ApiResponse(responseCode = "404", description = "Not Found", content = @Content)
    @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content)
    public ResponseEntity<DtoMessageResponse> postMessageFile(
        @RequestParam("file") MultipartFile multipart,
        @RequestParam("tipo") String tipo,
        @RequestParam("demandaId") Integer demandaId,
        @RequestParam("remetenteId") Integer remetenteId
    ) {
        DtoMessageResponse created = service.createMessageFile(
            multipart,
            tipo,
            demandaId,
            remetenteId
        );
        return created(HeaderConfig.getLocation(created.getId())).body(created);
    }

}
