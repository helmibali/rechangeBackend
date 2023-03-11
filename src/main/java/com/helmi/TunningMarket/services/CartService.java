package com.helmi.TunningMarket.services;
import com.helmi.TunningMarket.entities.*;


import com.helmi.TunningMarket.repositories.CartRepository;

import com.helmi.TunningMarket.repositories.ProduitRepository;
import com.helmi.TunningMarket.repositories.UserRepository;
import com.helmi.TunningMarket.requests.CartRequest;
import com.helmi.TunningMarket.requests.PanierRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {
    @Autowired
    CartRepository cartRepository;

    @Autowired
    ProduitService produitService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ProduitRepository produitRepository;






        public Cart saveCart(CartRequest cartRequest) {
            User user = userRepository.findByUsername(cartRequest.getUser());
            Produit produit = produitRepository.findById(cartRequest.getProduit()).get();
            Cart cart = new Cart();
            cart.setPrix(cartRequest.getPrix());
            cart.setDateCreation(cartRequest.getDateCreation());
            cart.setUser(user);
            cart.setProduit(produit);
            return  cartRepository.save(cart) ;
        }

        public List<Cart> findAllCart(){ return cartRepository.findAll();}

    public List<Cart> getCartByusername(String username){return cartRepository.getCartByusername(username);}
    public List<Cart> getCartByusernameEnCours(String username){return cartRepository.getCartByusernameEnCours(username);}
    public List<Cart> getCartByusernameInCmd(String username){return cartRepository.getCartByusernameInCmd(username);}

    public void DeleteCartById( long id ){ this.cartRepository.deleteById(id);  }

}
