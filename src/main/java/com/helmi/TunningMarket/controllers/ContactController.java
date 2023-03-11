package com.helmi.TunningMarket.controllers;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.helmi.TunningMarket.entities.Contact;
import com.helmi.TunningMarket.requests.ContactRequest;
import com.helmi.TunningMarket.requests.UserRequest;
import com.helmi.TunningMarket.services.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class ContactController {

    @Autowired
    ContactService contactService;

    @PostMapping("/contact")
    Contact saveContactForm(@Param("contact") String contact) throws JsonParseException, JsonMappingException, Exception
    {
        ContactRequest c = new ObjectMapper().readValue(contact, ContactRequest.class);
        return contactService.saveContact(c);
    }
    @GetMapping("/contact/liste")
    List<Contact> getAllContact(){
        return contactService.getAll();
    }
}
