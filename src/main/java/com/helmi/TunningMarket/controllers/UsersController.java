package com.helmi.TunningMarket.controllers;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.helmi.TunningMarket.entities.*;
import com.helmi.TunningMarket.repositories.RoleRepository;
import com.helmi.TunningMarket.repositories.UserRepository;
import com.helmi.TunningMarket.requests.ArticleRequest;
import com.helmi.TunningMarket.requests.EmailRequest;
import com.helmi.TunningMarket.requests.UserRequest;
import com.helmi.TunningMarket.response.ApiResponse;
import com.helmi.TunningMarket.services.EmailService;
import com.helmi.TunningMarket.services.UserService;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;



@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class UsersController {
    @Autowired
    UserRepository userRep;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    private ServletContext context;
    @Autowired
    UserService userService;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
    private PasswordEncoder passwordEncoder;



    @PostMapping("/signupwithimg")
    public User createUsser (@RequestParam("file") MultipartFile file,
                             @RequestParam("user") String user) throws JsonParseException, JsonMappingException, Exception
    {
        System.out.println("Save User...");
        UserRequest u = new ObjectMapper().readValue(user, UserRequest.class);
        boolean isExit = new File(context.getRealPath("/ImagesUser/")).exists();
        if (!isExit)
        {
            new File (context.getRealPath("/ImagesUser/")).mkdir();
            System.out.println("mk dir ImagesUser...");
        }
        System.out.println("Save User..2..");
        String filename = file.getOriginalFilename();
        String newFileName = FilenameUtils.getBaseName(filename)+"."+FilenameUtils.getExtension(filename);
        File serverFile = new File (context.getRealPath("/ImagesUser/"+File.separator+newFileName));
        try
        {
            System.out.println("Image");
            FileUtils.writeByteArrayToFile(serverFile,file.getBytes());

        }catch(Exception e) {
            e.printStackTrace();
        }
        System.out.println("Save User ..3..");
        u.setFilename(newFileName);
        return userService.saveUserWithImg(u);
    }

    @PostMapping("/signup")
    public User createUser (
            @RequestParam("user") String user) throws JsonParseException, JsonMappingException, Exception
    {
        System.out.println("Save User...");
        UserRequest u = new ObjectMapper().readValue(user, UserRequest.class);

        return userService.saveUser(u);
    }
    @PostMapping("/inscription")
    public User save(@RequestParam("file") MultipartFile file,@RequestParam("user") String user ) throws IOException {
        UserRequest u = new ObjectMapper().readValue(user, UserRequest.class);
        return userService.save(u,file);
    }
    @GetMapping("/imageuser/{id}")
    public ResponseEntity<?> downloadImage( @PathVariable Long id){
        byte[] imageData=userService.downloadImage(id);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("image/png"))
                .body(imageData);

    }

    @RequestMapping(value ="/login/{username}",method = RequestMethod.GET)
    public User getUserByUsernamePassword(@PathVariable("username") String username) {
        return userService.userByUserName(username);
    }

    @PostMapping("/user/social")
    public User createSocialUser (
                                  @RequestParam("user") String user) throws JsonParseException, JsonMappingException, Exception
    {
        System.out.println("Save User...");
        UserRequest u = new ObjectMapper().readValue(user, UserRequest.class);

        return userService.saveUserSocial(u);
    }

    @PostMapping("/user/add")
    public User createArticle (
            @RequestParam("user") String user) throws JsonParseException, JsonMappingException, Exception
    {
        System.out.println("Save User...");
        UserRequest u = new ObjectMapper().readValue(user, UserRequest.class);

        return userService.saveUser(u);
    }

    @PostMapping("/email")
    public Email email (
            @RequestParam("email") String email) throws JsonParseException, JsonMappingException, Exception
    {
        System.out.println("Save User...");
        EmailRequest e = new ObjectMapper().readValue(email, EmailRequest.class);

        return emailService.saveEmail(e);
    }




@PutMapping("/user/{id}")
public User updateUser(@RequestBody UserRequest u, @PathVariable Long id){
        return userService.updateUser(u,id);
}

@PutMapping("/userPw/{id}")
public User updatePwUser(@RequestParam("user") String user,
                         @PathVariable Long id)throws JsonParseException, JsonMappingException, Exception
{
    UserRequest u = new ObjectMapper().readValue(user, UserRequest.class);
    return userService.updatePassword(u,id);
}

    @PutMapping("/userPwEmail/{id}")
    public User updatePwUserEmail(@RequestParam("user") String user,
                             @PathVariable Long id)throws JsonParseException, JsonMappingException, Exception
    {
        UserRequest u = new ObjectMapper().readValue(user, UserRequest.class);
        return userService.updatePassword(u,id);
    }
    @PutMapping("/useractive/{id}")
    public User activeUser(@RequestParam("user") String user,
                             @PathVariable Long id)throws JsonParseException, JsonMappingException, Exception
    {
        UserRequest u = new ObjectMapper().readValue(user, UserRequest.class);
        return userService.activeUser(u,id);
    }
    @PutMapping("/usertoken/{username}")
    public User activeUser(@RequestParam("user") String user,
                           @PathVariable String username)throws JsonParseException, JsonMappingException, Exception
    {
        UserRequest u = new ObjectMapper().readValue(user, UserRequest.class);
        return userService.tokenPassword(u,username);
    }
@PutMapping("/userImg/{id}")
public User updateImageUser(@RequestParam("file") MultipartFile file,
                            @RequestParam("user") String user,
                            @PathVariable Long id)throws JsonParseException, JsonMappingException, Exception
{
    {
        System.out.println("Save User...");
        UserRequest u = new ObjectMapper().readValue(user, UserRequest.class);
        boolean isExit = new File(context.getRealPath("/ImagesUser/")).exists();
        if (!isExit)
        {
            new File (context.getRealPath("/ImagesUser/")).mkdir();
            System.out.println("mk dir ImagesUser...");
        }
        System.out.println("Save User..2..");
        String filename = file.getOriginalFilename();
        String newFileName = FilenameUtils.getBaseName(filename)+"."+FilenameUtils.getExtension(filename);
        File serverFile = new File (context.getRealPath("/ImagesUser/"+File.separator+newFileName));
        try
        {
            System.out.println("Image");
            FileUtils.writeByteArrayToFile(serverFile,file.getBytes());

        }catch(Exception e) {
            e.printStackTrace();
        }
        System.out.println("Save User ..3..");
        u.setFilename(newFileName);
        return userService.updateImageUser(u,id);
    }
}


    @GetMapping(path="/ImgUser/{id}")
    public byte[] getPhoto(@PathVariable("id") Long id) throws Exception{
        User User   = userRep.findById(id).get();
        return Files.readAllBytes(Paths.get(context.getRealPath("/ImagesUser/")+User.getFilename()));
    }
    @GetMapping(path="/ImgUser1/{username}")
    public byte[] getPhotoByUsername(@PathVariable("username") String username) throws Exception{
        User User   = userRep.findByUsername(username);
        return Files.readAllBytes(Paths.get(context.getRealPath("/ImagesUser/")+User.getFilename()));
    }
    @GetMapping(path="/userByToken/{token}")
    public User getUserByToken(@PathVariable String token){
        return userService.userByToken(token);
    }



    @DeleteMapping("/user/{id}")
    public ResponseEntity<?> DeleteProduit(@PathVariable int id){

        try {

            userService.DeleteUserById(id);

            ApiResponse res = new ApiResponse();
            res.setSuccess(true);
            res.setMessage("Utilistaeur supprimé avec succé!");
            return ResponseEntity.ok(res);
        }catch(Exception e) {
            return ResponseEntity.notFound().build().ok("Utilistaeur introuvable!");
        }
    }
    @GetMapping("/usernames")
public List<User> usernames(){return userRep.findUsername();}
@Autowired
    EmailService emailService;
    @GetMapping("/sendMail/{toEmail}/{subject}/{body}")
    public void sendMail(@PathVariable String toEmail,@PathVariable String body,@PathVariable String subject)
    { emailService.sendSimpleEmail(toEmail, body, subject);}




}
