package com.helmi.TunningMarket.controllers;


import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.helmi.TunningMarket.entities.Comment;
import com.helmi.TunningMarket.entities.Modele;
import com.helmi.TunningMarket.repositories.CommentRepository;
import com.helmi.TunningMarket.requests.CommentRequest;
import com.helmi.TunningMarket.requests.ModeleRequest;
import com.helmi.TunningMarket.response.ApiResponse;
import com.helmi.TunningMarket.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class CommentController {
    @Autowired
    CommentService commentService;
    @Autowired
    CommentRepository commentRepository;
   @GetMapping("/comments")
    public List<Comment> getAllComment(){return commentRepository.findAllOrderDate();}
    @GetMapping("/comments/article/{id}")
    public List<Comment> getAllComment(@PathVariable long id){return commentRepository.findAllOrderDateByArticleId(id);}
    @GetMapping("/comment/{id}")
    public Comment getCommentById(@PathVariable  long id){
       return commentService.getCommentById(id);}

    @PostMapping("/comment")
    public Comment saveComment(@RequestBody CommentRequest commentRequest){
       return commentService.saveComment(commentRequest);
    }

    @PostMapping("/comment1")
    public Comment saveComment(@RequestParam String comment) throws JsonParseException, JsonMappingException, Exception {
        CommentRequest c = new ObjectMapper().readValue(comment, CommentRequest.class);
        return commentService.saveComment(c);
    }
    @PutMapping("/comment/{id}")
    public Comment updateComment(@RequestBody CommentRequest commentRequest, Long id){
        return commentService.updateComment(commentRequest,id);
    }
    @DeleteMapping("comment/{id}")
    public ResponseEntity<?> DeleteComment(@PathVariable Long id){
        try {
            commentService.DeleteCommentById(id);
            ApiResponse res = new ApiResponse();
            res.setSuccess(true);
            res.setMessage("Commentaire supprimé avec succé!");
            return ResponseEntity.ok(res);
        }catch(Exception e) {
            return ResponseEntity.notFound().build().ok("Commentaire introuvable!");
        }
    }
}
