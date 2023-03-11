package com.helmi.TunningMarket.services;

import com.helmi.TunningMarket.entities.ImageData;
import com.helmi.TunningMarket.entities.Professionnel;
import com.helmi.TunningMarket.repositories.ProfessionnelRepository;
import com.helmi.TunningMarket.requests.ProfessionnelReq;
import com.helmi.TunningMarket.util.ImageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
public class ProfessionnelService {
    @Autowired
    ProfessionnelRepository professionnelRepository;

    public String save(ProfessionnelReq pr ,MultipartFile file) throws IOException {
        Professionnel professionnel = professionnelRepository.save(Professionnel.builder()
                .nom(pr.getNom())
                .prenom(pr.getPrenom())
                .imageData(ImageUtils.compressImage(file.getBytes())).build());
              if (professionnel != null) {
               return "file uploaded successfully : " + file.getOriginalFilename();
        }
        return null;
    }


    public byte[] downloadImage(String nom, Long id){
        Optional<Professionnel> dbImageData = professionnelRepository.findByNomAndId(nom,id);
        byte[] images=ImageUtils.decompressImage(dbImageData.get().getImageData());
        return images;
    }
}
