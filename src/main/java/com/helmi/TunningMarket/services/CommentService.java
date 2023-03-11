package com.helmi.TunningMarket.services;

import com.helmi.TunningMarket.entities.*;
import com.helmi.TunningMarket.repositories.ArticleRepository;
import com.helmi.TunningMarket.repositories.CommentRepository;
import com.helmi.TunningMarket.repositories.UserRepository;
import com.helmi.TunningMarket.requests.CommentRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    @Autowired
    CommentRepository commentRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ArticleRepository articleRepository;

    public Comment getCommentById(Long id){return commentRepository.findById(id).get();}
    public List<Comment> getAllComment(){return commentRepository.findAllOrderDate();}
    public List<Comment> getAllCommentByArticle(long id){return commentRepository.findAllOrderDateByArticleId(id);}
    public void DeleteComment(Comment comment){ commentRepository.delete(comment);}
    public void DeleteCommentById(Long id){ commentRepository.deleteById(id);}

    public Comment saveComment(CommentRequest commentRequest){
        Comment comment = new Comment();
        Article article = articleRepository.findById(commentRequest.getArticle()).get();
        User user = userRepository.findByUsername(commentRequest.getUser());
        comment.setTexte(commentRequest.getTexte());
        comment.setDateComment(commentRequest.getDateComment());
        comment.setParentId(commentRequest.getParentId());
        comment.setUser(user);
        comment.setArticle(article);
      return commentRepository.save(comment);}


    public Comment updateComment(CommentRequest commentRequest, Long id){
        Comment comment = commentRepository.findById(id).get();
        Article article = articleRepository.findById(commentRequest.getArticle()).get();
        User user = userRepository.findByUsername(commentRequest.getUser());
        comment.setTexte(commentRequest.getTexte());
        comment.setDateComment(commentRequest.getDateComment());
        comment.setParentId(commentRequest.getParentId());
        comment.setUser(user);
        comment.setArticle(article);
        return commentRepository.save(comment);}
}
