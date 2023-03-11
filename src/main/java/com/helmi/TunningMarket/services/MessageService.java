package com.helmi.TunningMarket.services;

import com.helmi.TunningMarket.entities.Cart;
import com.helmi.TunningMarket.entities.Message;
import com.helmi.TunningMarket.entities.Produit;
import com.helmi.TunningMarket.entities.User;
import com.helmi.TunningMarket.repositories.MessageRepository;
import com.helmi.TunningMarket.repositories.UserRepository;
import com.helmi.TunningMarket.requests.CartRequest;
import com.helmi.TunningMarket.requests.MessageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class MessageService {
    @Autowired
    MessageRepository messageRepository;
    @Autowired
    UserRepository userRepository;
    public List<Message> findAll(){return messageRepository.findAll();}
    public List<Message> findAllByEmiter( String username){return messageRepository.getMsgByEmiter( username);}
    public List<Message> findAllByEmiterNonLu( String username){return messageRepository.getMsgByEmiterNonLus( username);}
    public List<Message> findAllByUser( String username){return messageRepository.getAllMsgForUser( username);}
    public List<Message> findAllByAuteur(String username){return messageRepository.getMsgByAuteur(username);}
    public Set<User> findContact(String username){return messageRepository.getAllUser(username);}
    public List<Message> findDiscussion(String username1,String username2){return messageRepository.getDiscussion(username1,username2);}
    public Message saveMessage(MessageRequest messageRequest) {
        User auteur = userRepository.findByUsername(messageRequest.getAuteur());
        User emiter = userRepository.findById(messageRequest.getEmiter()).get();
        Message message = new Message();
        message.setAuteur(auteur);
        message.setVu(messageRequest.isVu());
        message.setEmiter(emiter);
        message.setMessage(messageRequest.getMessage());
        message.setDateCreation(messageRequest.getDateCreation());
        return  messageRepository.save(message);
    }
    public Message readMessage(MessageRequest messageRequest) {

        Message message = messageRepository.getById(messageRequest.getId());

        message.setVu(messageRequest.isVu());

        return  messageRepository.save(message);
    }

}
