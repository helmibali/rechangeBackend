package com.helmi.TunningMarket.controllers;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.helmi.TunningMarket.entities.Article;
import com.helmi.TunningMarket.entities.Produit;
import com.helmi.TunningMarket.repositories.ArticleRepository;
import com.helmi.TunningMarket.requests.ArticleRequest;
import com.helmi.TunningMarket.requests.ProduitRequest;
import com.helmi.TunningMarket.response.ApiResponse;
import com.helmi.TunningMarket.services.ArticleService;
import com.helmi.TunningMarket.services.FlickrServiceImpl;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class ArticleController {
    @Autowired
    FlickrServiceImpl flickrServiceImpl;
    @Autowired
    ArticleService articleService;
    @Autowired
    private ServletContext context;
    @Autowired
    private ArticleRepository articleRepository;
    @GetMapping("/articles")
    public List<Article> getAllArticles(){return articleService.getAllArticles();}
    @GetMapping("/article/{id}")
    public Article getArticleById(@PathVariable Long id){return articleService.getArticleById(id);}
    @GetMapping("/article-par-utlisateur/{username}")
    public List<Article> getArticleByUser(@PathVariable String username){return articleService.getAllArticlesByUser(username);}

    @PostMapping("/articleWithImg")
    public Article createArticleWithImg (@RequestParam("file") MultipartFile file, @RequestParam("article") String article)
            throws JsonParseException, JsonMappingException, Exception
    {
        ArticleRequest a = new ObjectMapper().readValue(article, ArticleRequest.class);
        boolean isExit = new File(context.getRealPath("/ImagesArticle/")).exists();
        if (!isExit) { new File (context.getRealPath("/ImagesArticle/")).mkdir();}
        String filename = file.getOriginalFilename();
        String newFileName = FilenameUtils.getBaseName(filename)+"."+FilenameUtils.getExtension(filename);
        File serverFile = new File (context.getRealPath("/ImagesArticle/"+File.separator+newFileName));
        try {FileUtils.writeByteArrayToFile(serverFile,file.getBytes());}
        catch(Exception e) {e.printStackTrace();}
        a.setFilename(newFileName);
        return articleService.saveArticleWithImg(a);
    }

    @GetMapping("/imagearticle/{id}")
    public ResponseEntity<?> downloadImage( @PathVariable Long id){
        byte[] imageData=articleService.downloadImage(id);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("image/png"))
                .body(imageData);

    }


    @PostMapping("/articleimage")
    public Article save(@RequestParam("file") MultipartFile file,@RequestParam("article") String article ) throws IOException {
        ArticleRequest a = new ObjectMapper().readValue(article, ArticleRequest.class);
        return articleService.save(a,file);
    }



    @PostMapping("/article")
    public Article createArticle (@RequestParam("article") String article)
            throws JsonParseException, JsonMappingException, Exception
    {
        ArticleRequest a = new ObjectMapper().readValue(article, ArticleRequest.class);
        /*
        boolean isExit = new File(context.getRealPath("/ImagesArticle/")).exists();
        if (!isExit) { new File (context.getRealPath("/ImagesArticle/")).mkdir();}
        String filename = file.getOriginalFilename();
        String newFileName = FilenameUtils.getBaseName(filename)+"."+FilenameUtils.getExtension(filename);
        File serverFile = new File (context.getRealPath("/ImagesArticle/"+File.separator+newFileName));
        try {FileUtils.writeByteArrayToFile(serverFile,file.getBytes());}
        catch(Exception e) {e.printStackTrace();}
        a.setFilename(newFileName);

         */
        return articleService.saveArticle(a);
    }
    @PutMapping("/article")
    public Article updateArticle (@RequestParam("file") MultipartFile file, @RequestParam("article") String article,
                                  @PathVariable Long id)
            throws JsonParseException, JsonMappingException, Exception
    {
        ArticleRequest a = new ObjectMapper().readValue(article, ArticleRequest.class);
        boolean isExit = new File(context.getRealPath("/ImagesArticle/")).exists();
        if (!isExit) { new File (context.getRealPath("/ImagesArticle/")).mkdir();}
        String filename = file.getOriginalFilename();
        String newFileName = FilenameUtils.getBaseName(filename)+"."+FilenameUtils.getExtension(filename);
        File serverFile = new File (context.getRealPath("/ImagesArticle/"+File.separator+newFileName));
        try {FileUtils.writeByteArrayToFile(serverFile,file.getBytes());}
        catch(Exception e) {e.printStackTrace();}
        a.setFilename(newFileName);
        return articleService.updateArticle(a,id);
    }
    @DeleteMapping("/article/{id}")
    public ResponseEntity<?> DeleteArticle(@PathVariable Long id){

        try {

            articleService.deleteById(id);

            ApiResponse res = new ApiResponse();
            res.setSuccess(true);
            res.setMessage("Article supprimé avec succé!");
            return ResponseEntity.ok(res);
        }catch(Exception e) {
            return ResponseEntity.notFound().build().ok("Article introuvable!");
        }
    }

    @GetMapping(path="/imga/{id}")
    public byte[] getPhoto(@PathVariable("id") Long id) throws Exception{
        Article Article   = articleRepository.findById(id).get();
        return Files.readAllBytes(Paths.get(context.getRealPath("/ImagesArticle/")+Article.getFilename()));
    }

    @PostMapping("/articleflickr")
    public Article createArticleWithImgflicker (@RequestParam("file") MultipartFile file, @RequestParam("article") String article)
            throws JsonParseException, JsonMappingException, Exception
    {
        ArticleRequest a = new ObjectMapper().readValue(article, ArticleRequest.class);
        /*
        boolean isExit = new File(context.getRealPath("/ImagesArticle/")).exists();
        if (!isExit) { new File (context.getRealPath("/ImagesArticle/")).mkdir();}
        String filename = file.getOriginalFilename();
        String newFileName = FilenameUtils.getBaseName(filename)+"."+FilenameUtils.getExtension(filename);
        File serverFile = new File (context.getRealPath("/ImagesArticle/"+File.separator+newFileName));
        try {FileUtils.writeByteArrayToFile(serverFile,file.getBytes());}
        catch(Exception e) {e.printStackTrace();}

         */
       InputStream stream = file.getInputStream();
       String photoUrl = flickrServiceImpl.savePhoto(stream, a.getTitle());
        a.setFilename(photoUrl);
        return articleService.saveArticleWithImg(a);
    }
}
