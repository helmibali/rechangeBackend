package com.helmi.TunningMarket.services;

import com.helmi.TunningMarket.entities.Contact;
import com.helmi.TunningMarket.repositories.ContactRepository;
import com.helmi.TunningMarket.requests.ContactRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactService {
    @Autowired
    ContactRepository contactRepository;


    public Contact saveContact(ContactRequest contact){
        Contact c = new Contact();
        c.setNom(contact.getNom());
        c.setPrenom(contact.getPrenom());
        c.setEmail(contact.getEmail());
        c.setMessage(contact.getMessage());
        return contactRepository.save(c);
    }

    public List<Contact> getAll(){
        return contactRepository.findAll();
    }
}
