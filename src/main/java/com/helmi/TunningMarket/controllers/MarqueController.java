package com.helmi.TunningMarket.controllers;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.helmi.TunningMarket.entities.Marque;
import com.helmi.TunningMarket.entities.Modele;
import com.helmi.TunningMarket.entities.Produit;
import com.helmi.TunningMarket.repositories.MarqueRepository;
import com.helmi.TunningMarket.requests.MarqueRequest;
import com.helmi.TunningMarket.requests.ProfessionnelReq;
import com.helmi.TunningMarket.response.ApiResponse;
import com.helmi.TunningMarket.services.MarqueService;
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
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class MarqueController {
@Autowired
    MarqueRepository marqueRepository;
@Autowired
    MarqueService marqueService;
@Autowired
    ServletContext context;

@GetMapping("/marques")
public List<Marque> getAllMarques(){
    return marqueService.getMarques();
}


@PostMapping("/addMarque")
    public Marque createMarque(@RequestParam("file") MultipartFile file,
 @RequestParam("marque") String marque) throws JsonParseException, JsonMappingException, Exception
{
    Marque m = new ObjectMapper().readValue(marque, Marque.class);
    boolean isExit = new File(context.getRealPath("/ImagesMarque/")).exists();
    if (!isExit)
    {
        new File (context.getRealPath("/ImagesMarque/")).mkdir();
    }
    String filename = file.getOriginalFilename();
    String newFileName = FilenameUtils.getBaseName(filename)+"."+ FilenameUtils.getExtension(filename);
    File serverFile = new File (context.getRealPath("/ImagesMarque/"+File.separator+newFileName));
    try
    {
        FileUtils.writeByteArrayToFile(serverFile,file.getBytes());
    }catch (Exception e){
        e.printStackTrace();
    }
    m.setFilename(newFileName);
   return marqueRepository.save(m);
}

    @PostMapping("/marque")
    public ResponseEntity<?> uploadImage(@RequestParam("file") MultipartFile file,@RequestParam("marque") String marque )
            throws IOException {

        MarqueRequest mr = new ObjectMapper().readValue(marque, MarqueRequest.class);
        String m = marqueService.save(mr,file);
        return ResponseEntity.status(HttpStatus.OK)
                .body(m);
    }



    @GetMapping(path="/imgmarque/{id}")
    public byte[] getPhoto(@PathVariable("id") int id) throws Exception{
        Marque Marque   = marqueRepository.findById(id);
        return Files.readAllBytes(Paths.get(context.getRealPath("/ImagesMarque/")+Marque.getFilename()));

    }

    @GetMapping("/imagemarque/{id}")
    public ResponseEntity<?> downloadImage( @PathVariable int id){
        byte[] imageData=marqueService.downloadImage(id);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("image/png"))
                .body(imageData);

    }



    /*
    @PutMapping("marque")
    public Marque updateMarque(@RequestBody MarqueRequest marqueRequest, @PathVariable int id){
        return marqueService.updateMarque(marqueRequest, id);
    }

     */


    @GetMapping("/marque/{id}")
    public Marque getMarqueById(@PathVariable int id){
        return marqueService.getMarqueById(id);
    }

    @DeleteMapping("/marque/{id}")
    public ResponseEntity<?> DeleteMarque(@PathVariable int id){

        try {

            marqueService.DeleteMarqueById(id);

            ApiResponse res = new ApiResponse();
            res.setSuccess(true);
            res.setMessage("Modele supprimé avec succé!");
            return ResponseEntity.ok(res);
        }catch(Exception e) {
            return ResponseEntity.notFound().build().ok("Marque introuvable!");
        }
    }

    @GetMapping ("/getAll")
    public ResponseEntity<List<String>> getAll()
    {
        List<String> listMarq = new ArrayList<String>();
        String filesPath = context.getRealPath("/Images");
        File filefolder = new File(filesPath);
        if (filefolder != null)
        {
            for (File file :filefolder.listFiles())
            {
                if(!file.isDirectory())
                {
                    String encodeBase64 = null;
                    try {
                        String extension = FilenameUtils.getExtension(file.getName());
                        FileInputStream fileInputStream = new FileInputStream(file);
                        byte[] bytes = new byte[(int)file.length()];
                        fileInputStream.read(bytes);
                        encodeBase64 = Base64.getEncoder().encodeToString(bytes);
                        listMarq.add("data:image/"+extension+";base64,"+encodeBase64);
                        fileInputStream.close();


                    }catch (Exception e){

                    }
                }
            }
        }
        return new ResponseEntity<List<String>>(listMarq,HttpStatus.OK);
    }

    @PutMapping("/marque")
    public Marque updateMarque(@RequestParam("file") MultipartFile file,
                               @RequestParam("marque") String marque) throws JsonParseException, JsonMappingException, Exception
    {
        Marque m = new ObjectMapper().readValue(marque, Marque.class);
        boolean isExit = new File(context.getRealPath("/Images/")).exists();
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
        m.setFilename(newFileName);
        return marqueRepository.save(m);
    }
    /*@PutMapping("marque/{id}")
    public Marque updateMarque(@RequestParam("file") MultipartFile file,
                               @RequestParam("marque") String marque,
                               MarqueRequest marqueRequest,
                               @PathVariable int id) throws JsonParseException, JsonMappingException, Exception
    {
        Marque m = marqueRepository.findById(id);

        //Marque m = new ObjectMapper().readValue(marque, Marque.class);
        boolean isExit = new File(context.getRealPath("/Images/")).exists();
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
        m.setLibelleMarque(marqueRequest.libelleMarque);
        m.setFilename(newFileName);

        return marqueRepository.save(m);
    }

     */

}
