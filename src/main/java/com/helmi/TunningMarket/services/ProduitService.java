package com.helmi.TunningMarket.services;

import com.helmi.TunningMarket.entities.*;
import com.helmi.TunningMarket.repositories.*;
import com.helmi.TunningMarket.requests.ProduitRequest;
import com.helmi.TunningMarket.util.ImageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProduitService {

    @Autowired
    private ProduitRepository produitRepository;

    @Autowired
    private CategorieRepository categorieRepository;

    @Autowired
    private ModeleRepository modeleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DelegationRepository delegationRepository;


    public List<Produit> getProduits(){
        return produitRepository.findAllOrderDate();
    }


    public Produit getProduitById(Long id){ return produitRepository.findById(id).get();};

    public Produit saveProduit(ProduitRequest produitRequest) {
        User user = userRepository.findByUsername(produitRequest.getUser());
        Categorie categorie = categorieRepository.findById(produitRequest.categorie_id);

        Delegation delegation = delegationRepository.findById(produitRequest.getDelegation_id()).get();
        Produit produit = new Produit();
        produit.setNomProduit(produitRequest.nomProduit);
        produit.setFilename(produitRequest.filename);
        produit.setPrixProduit(produitRequest.prixProduit);
        produit.setDateCreation(produitRequest.dateCreation);
        produit.setDescription(produitRequest.getDescription());
        produit.setCarburant(produitRequest.getCarburant());
        produit.setAnnee(produitRequest.getAnnee());
        produit.setEnabled(produitRequest.getEnabled());
        produit.setUser(user);
        produit.setCategorie(categorie);
        produit.setDelegation(delegation);


        produit.setModeles(produitRequest.modeles
                .stream()
                .map(modeles ->{
                    Modele mods = modeles;
                    if(mods.getId()>0){
                        mods = modeleRepository.findById(mods.getId());
                    }
                    mods.getProduits().add(produit);
                    return mods;
         }
         ).collect(Collectors.toSet()));

        return  produitRepository.save(produit) ;
    }


    public void DeleteProduitById( long id ){ this.produitRepository.deleteById(id);  }


    public Produit updateProduit(ProduitRequest produitRequest,long id ){
        Delegation delegation = delegationRepository.findById(produitRequest.getDelegation_id()).get();
        User user = userRepository.findByUsername(produitRequest.getUser());
            Categorie categorie = categorieRepository.findById(produitRequest.categorie_id);
            Produit produit = produitRepository.findById(id).get();
            produit.setFilename(produitRequest.filename);
            produit.setNomProduit(produitRequest.nomProduit);
            produit.setPrixProduit(produitRequest.prixProduit);
            produit.setDateCreation(produitRequest.dateCreation);
            produit.setEnabled(produitRequest.getEnabled());
            produit.setUser(user);
            produit.setCategorie(categorie);
            produit.setDelegation(delegation);
            produit.setModeles(produitRequest.modeles
                    .stream()
                    .map(modeles ->{
                                Modele mods = modeles;
                                if(mods.getId()>0){
                                    mods = modeleRepository.findById(mods.getId());
                                }
                                mods.getProduits().add(produit);
                                return mods;
                            }
                    ).collect(Collectors.toSet()));
            // success 200
            return this.produitRepository.save(produit);
    }
    public Produit activeProduit(ProduitRequest produitRequest,long id ){
        Produit produit = produitRepository.findById(id).get();
        produit.setEnabled(produitRequest.getEnabled());
        // success 200
        return this.produitRepository.save(produit);
    }

    public List<Produit> ProduitByCategorie_id(int id){
        return produitRepository.findByCategorie_Id(id);
    }


    public Produit save(ProduitRequest produitRequest , MultipartFile file) throws IOException {
        User user = userRepository.findByUsername(produitRequest.getUser());
        Categorie categorie = categorieRepository.findById(produitRequest.categorie_id);

        Delegation delegation = delegationRepository.findById(produitRequest.getDelegation_id()).get();
        Produit produit = new Produit();
        produit.setNomProduit(produitRequest.nomProduit);
        produit.setFilename(produitRequest.filename);
        produit.setPrixProduit(produitRequest.prixProduit);
        produit.setDateCreation(produitRequest.dateCreation);
        produit.setDescription(produitRequest.getDescription());
        produit.setCarburant(produitRequest.getCarburant());
        produit.setAnnee(produitRequest.getAnnee());
        produit.setEnabled(produitRequest.getEnabled());
        produit.setUser(user);
        produit.setCategorie(categorie);
        produit.setDelegation(delegation);
        produit.setImageData(ImageUtils.compressImage(file.getBytes()));


        produit.setModeles(produitRequest.modeles
                .stream()
                .map(modeles ->{
                            Modele mods = modeles;
                            if(mods.getId()>0){
                                mods = modeleRepository.findById(mods.getId());
                            }
                            mods.getProduits().add(produit);
                            return mods;
                        }
                ).collect(Collectors.toSet()));

        return  produitRepository.save(produit) ;
    }
    public byte[] downloadImage(Long id){
        Produit dbImageData = produitRepository.findById(id).get();
        byte[] images=ImageUtils.decompressImage(dbImageData.getImageData());
        return images;
    }
}




