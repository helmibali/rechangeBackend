package com.helmi.TunningMarket.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.helmi.TunningMarket.entities.Professionnel;
import com.helmi.TunningMarket.repositories.ProfessionnelRepository;
import com.helmi.TunningMarket.requests.ProfessionnelReq;
import com.helmi.TunningMarket.services.ProfessionnelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class professionnelController {
    @Autowired
    ProfessionnelService professionnelService;
    @Autowired
    ProfessionnelRepository professionnelRepository;
    @PostMapping("/pro")
    public ResponseEntity<?> uploadImage(@RequestParam("image") MultipartFile file,@RequestParam("pro") String p )
            throws IOException {

        ProfessionnelReq pr = new ObjectMapper().readValue(p, ProfessionnelReq.class);
        String professionnel = professionnelService.save(pr,file);
        return ResponseEntity.status(HttpStatus.OK)
                .body(professionnel);
    }

    @GetMapping("/pros")
    public List<Professionnel> getAll(){
        return professionnelRepository.findAll();
    }

    @GetMapping("/imagepro/{nom}/{id}")
    public ResponseEntity<?> downloadImage(@PathVariable String nom, @PathVariable Long id){
        byte[] imageData=professionnelService.downloadImage(nom, id);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("image/png"))
                .body(imageData);

    }


}
/*
    @PostMapping("/image")
    public ResponseEntity<?> uploadImage(@RequestParam("image")MultipartFile file) throws IOException {
        String uploadImage = service.uploadImage(file);
        return ResponseEntity.status(HttpStatus.OK)
                .body(uploadImage);
    }

        @PostMapping("/signup")
    public User createUser (
            @RequestParam("user") String user) throws JsonParseException, JsonMappingException, Exception
    {
        System.out.println("Save User...");
        UserRequest u = new ObjectMapper().readValue(user, UserRequest.class);

        return userService.saveUser(u);
    }
 */