package com.helmi.TunningMarket.services;

import com.helmi.TunningMarket.entities.*;
import com.helmi.TunningMarket.repositories.ArticleRepository;
import com.helmi.TunningMarket.repositories.CommentRepository;
import com.helmi.TunningMarket.repositories.UserRepository;
import com.helmi.TunningMarket.requests.ArticleRequest;
import com.helmi.TunningMarket.util.ImageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArticleService {
    @Autowired
    ArticleRepository articleRepository;
    @Autowired
    CommentRepository commentRepository;
    @Autowired
    UserRepository userRepository;

    public void delete(Article article){articleRepository.delete(article);}

    public void deleteById(Long id){articleRepository.deleteById(id);}

    public Article getArticleById(Long id){return articleRepository.findById(id).get();}

    public List<Article> getAllArticles(){return articleRepository.findAllOrderDate();}

    public List<Article> getAllArticlesByUser(String username){return articleRepository.findArticlesByUser(username);}

    public Article saveArticleWithImg(ArticleRequest articleRequest){
        User user = userRepository.findByUsername(articleRequest.getUser());
        Article article = new Article();
        article.setTitle(articleRequest.getTitle());
        article.setDateCreation(articleRequest.getDateCreation());
        article.setFilename(articleRequest.getFilename());
        article.setText(articleRequest.getText());
        article.setUser(user);
        return articleRepository.save(article);}

    public Article save(ArticleRequest articleRequest, MultipartFile file) throws IOException {
        User user = userRepository.findByUsername(articleRequest.getUser());
        Article article = new Article();
        article.setTitle(articleRequest.getTitle());
        article.setDateCreation(articleRequest.getDateCreation());
        article.setFilename(file.getOriginalFilename().toLowerCase()+file.hashCode());
        article.setText(articleRequest.getText());
        article.setUser(user);
        article.setImageData(ImageUtils.compressImage(file.getBytes()));
        return articleRepository.save(article);}

    public byte[] downloadImage(Long id){
        Article dbImageData = articleRepository.findById(id).get();
        byte[] images=ImageUtils.decompressImage(dbImageData.getImageData());
        return images;
    }

    public Article saveArticle(ArticleRequest articleRequest){
        User user = userRepository.findByUsername(articleRequest.getUser());
        Article article = new Article();
        article.setTitle(articleRequest.getTitle());
        article.setDateCreation(articleRequest.getDateCreation());
       // article.setFilename(articleRequest.getFilename());
        article.setText(articleRequest.getText());
        article.setUser(user);
        return articleRepository.save(article);}

    public Article updateArticle(ArticleRequest articleRequest, Long id){
        User user = userRepository.findByUsername(articleRequest.getUser());
        Article article = articleRepository.findById(id).get();
        article.setTitle(articleRequest.getTitle());
        article.setDateCreation(articleRequest.getDateCreation());
        article.setFilename(articleRequest.getFilename());
        article.setText(articleRequest.getText());
        article.setUser(user);

        return articleRepository.save(article);}

    /*
    public void  saveProductToDB(MultipartFile file,String name,String description
            ,int price)
    {
        Product p = new Product();
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        if(fileName.contains(".."))
        {
            System.out.println("not a a valid file");
        }
        try {
            p.setImage(Base64.getEncoder().encodeToString(file.getBytes()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        p.setDescription(description);

        p.setName(name);
        p.setPrice(price);

        productRepo.save(p);
    }

     */

    public Article saveArticle2(ArticleRequest articleRequest){
        User user = userRepository.findByUsername(articleRequest.getUser());
        Article article = new Article();
        article.setTitle(articleRequest.getTitle());
        article.setDateCreation(articleRequest.getDateCreation());
        // article.setFilename(articleRequest.getFilename());
        article.setText(articleRequest.getText());
        article.setUser(user);
        return articleRepository.save(article);}
}
