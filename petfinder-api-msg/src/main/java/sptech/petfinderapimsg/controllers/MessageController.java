package sptech.petfinderapimsg.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sptech.petfinderapimsg.services.MessageService;

@RestController
@RequestMapping("/message")
public class MessageController {
    
    @Autowired
    private MessageService service;

    @GetMapping
    public ResponseEntity<String> getMessage() {
        return ResponseEntity.ok(service.getMessage());
    }

}
