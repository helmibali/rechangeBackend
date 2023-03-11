package com.helmi.TunningMarket.controllers;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.helmi.TunningMarket.entities.Cart;
import com.helmi.TunningMarket.entities.Message;
import com.helmi.TunningMarket.entities.User;
import com.helmi.TunningMarket.requests.CartRequest;
import com.helmi.TunningMarket.requests.MessageRequest;
import com.helmi.TunningMarket.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class MessageController {
    @Autowired
    MessageService messageService;
    @GetMapping("/message-par-auteur/{username}")
    List<Message> findByAuteur (@PathVariable String username){return messageService.findAllByAuteur(username);}

    @GetMapping("/message-par-emiteur/{username}")
    List<Message> findByEmiter(@PathVariable String username){return messageService.findAllByEmiter(username);}
    @GetMapping("/message-par-emiteur-unread/{username}")
    List<Message> findByEmiterUnread(@PathVariable String username){return messageService.findAllByEmiterNonLu(username);}

    @GetMapping("/message-par-user/{username}")
    List<Message> findByUser(@PathVariable String username){return messageService.findAllByUser(username);}

    @GetMapping("/user-contact/{username}")
    Set<User> findContact(@PathVariable String username){return messageService.findContact(username);}

    @GetMapping("/discussion/{username1}/{username2}")
    List<Message> findByUser(@PathVariable String username1, @PathVariable String username2){
        return messageService.findDiscussion(username1,username2);}

    @PostMapping("/message")
    public Message save (
            @RequestParam("message") String message) throws JsonParseException, JsonMappingException, Exception
    {
        System.out.println("Save message...");
        MessageRequest m = new ObjectMapper().readValue(message, MessageRequest.class);
        return messageService.saveMessage(m);
    }
    @PutMapping("/message-read/{id}")
    public Message read (
            @RequestParam("message") String message) throws JsonParseException, JsonMappingException, Exception
    {
        System.out.println("Save message...");
        MessageRequest m = new ObjectMapper().readValue(message, MessageRequest.class);
        return messageService.readMessage(m);
    }
}
