package com.helmi.TunningMarket.services;

import com.helmi.TunningMarket.entities.*;
import com.helmi.TunningMarket.repositories.MarqueRepository;
import com.helmi.TunningMarket.requests.CityRequest;
import com.helmi.TunningMarket.requests.MarqueRequest;
import com.helmi.TunningMarket.requests.ModeleRequest;
import com.helmi.TunningMarket.requests.ProfessionnelReq;
import com.helmi.TunningMarket.util.ImageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Service
public class MarqueService {
@Autowired
    MarqueRepository marqueRepository;

    public List<Marque> getMarques(){
        return marqueRepository.findAll();
    };
    public Marque getMarqueById(int id){ return marqueRepository.findById(id);};


    public Marque updateMarque(MarqueRequest marqueRequest, int id){
        Marque marque = marqueRepository.findById(id);
        marque.setLibelleMarque(marqueRequest.libelleMarque);
        return  marqueRepository.save(marque) ;
    }
    public void DeleteMarqueById( int id ){ this.marqueRepository.deleteById(id);  }

    public String save(MarqueRequest marqueRequest , MultipartFile file) throws IOException {
        Marque marque = marqueRepository.save(Marque.builder()
                .libelleMarque(marqueRequest.getLibelleMarque())
                .imageData(ImageUtils.compressImage(file.getBytes())).build());
        if (marque != null) {
            return "marque ajouté avec succés : " + file.getOriginalFilename();
        }
        return null;
    }


    public byte[] downloadImage(int id){
        Marque dbImageData = marqueRepository.findById(id);
        byte[] images=ImageUtils.decompressImage(dbImageData.getImageData());
        return images;
    }

}
