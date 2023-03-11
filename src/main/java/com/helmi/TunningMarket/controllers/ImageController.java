package com.helmi.TunningMarket.controllers;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.helmi.TunningMarket.entities.Image;
import com.helmi.TunningMarket.entities.ImageData;
import com.helmi.TunningMarket.entities.Marque;
import com.helmi.TunningMarket.repositories.ImageDataRepository;
import com.helmi.TunningMarket.repositories.ImageRepository;
import com.helmi.TunningMarket.services.StorageService;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class ImageController {
    @Autowired
    ImageRepository imageRepository;
    @Autowired
    ImageDataRepository imageDataRepository;
    @Autowired
    private StorageService storageService;
    @Autowired
    ServletContext context;
    @Autowired
    private StorageService service;

    @PostMapping("/image")
    public ResponseEntity<?> uploadImage(@RequestParam("image")MultipartFile file) throws IOException {
        String uploadImage = service.uploadImage(file);
        return ResponseEntity.status(HttpStatus.OK)
                .body(uploadImage);
    }

    @GetMapping("/image/{fileName}{id}")
    public ResponseEntity<?> downloadImage(@PathVariable String fileName){
        byte[] imageData=service.downloadImage(fileName);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("image/png"))
                .body(imageData);

    }




    @PostMapping("addImage")
    public Image createImage(@RequestParam("file") MultipartFile file,
                             @RequestParam("image") String image) throws JsonParseException, JsonMappingException, Exception
    {
        Image i = new ObjectMapper().readValue(image, Image.class);
        boolean isExit = new File(context.getRealPath("/ImagesG/")).exists();
        if (!isExit)
        {
            new File (context.getRealPath("/Images/")).mkdir();
        }
        String filename = file.getOriginalFilename();
        String newFileName = FilenameUtils.getBaseName(filename)+"."+ FilenameUtils.getExtension(filename);
        File serverFile = new File (context.getRealPath("/Images/"+File.separator+newFileName));
        try
        {
            FileUtils.writeByteArrayToFile(serverFile,file.getBytes());
        }catch (Exception e){
            e.printStackTrace();
        }
        i.setFilename(newFileName);
        return imageRepository.save(i);
    }




}
