package com.helmi.TunningMarket.controllers;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.helmi.TunningMarket.entities.Cart;
import com.helmi.TunningMarket.entities.Panier;
import com.helmi.TunningMarket.requests.CartRequest;
import com.helmi.TunningMarket.requests.PanierRequest;
import com.helmi.TunningMarket.response.ApiResponse;
import com.helmi.TunningMarket.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class CartController {
    @Autowired
   private CartService cartService;

    @PostMapping("/panier2")
    public Cart createArticle (
            @RequestParam("cart") String panier) throws JsonParseException, JsonMappingException, Exception
    {
        System.out.println("Save panier...");
        CartRequest p = new ObjectMapper().readValue(panier, CartRequest.class);

        return cartService.saveCart(p);
    }

    @PostMapping("/c")
    public Cart commander (
            @RequestBody CartRequest cart)
    {return cartService.saveCart(cart);}

    @GetMapping("/liste-cart")
    List<Cart> findAll(){return cartService.findAllCart();}
    @GetMapping("/liste-cart/{username}")
    List<Cart> findByUsername(@PathVariable String username){return cartService.getCartByusername(username);}

    @GetMapping("/liste-cart-en-cours/{username}")
    List<Cart> findByUsernameEnCours(@PathVariable String username){return cartService.getCartByusernameEnCours(username);}

    @GetMapping("/liste-cart-en-commande/{username}")
    List<Cart> findByUsernameEnCmd(@PathVariable String username){return cartService.getCartByusernameInCmd(username);}
    @DeleteMapping("/cart/{id}")
    public ResponseEntity<?> DeleteProduit(@PathVariable long id){

        try {

            cartService.DeleteCartById(id);

            ApiResponse res = new ApiResponse();
            res.setSuccess(true);
            res.setMessage("Produit supprimé avec succé!");
            return ResponseEntity.ok(res);
        }catch(Exception e) {
            return ResponseEntity.notFound().build().ok("Produit introuvable!");
        }
    }



}
