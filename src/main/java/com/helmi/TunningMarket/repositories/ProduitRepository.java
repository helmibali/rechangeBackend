package com.helmi.TunningMarket.repositories;

import com.helmi.TunningMarket.entities.Produit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;


@Repository
public interface ProduitRepository extends JpaRepository<Produit,Long> {
List<Produit> findByCategorie_Id(int id);

    @Query("SELECT p FROM Produit p  ORDER BY p.dateCreation DESC ")
    List<Produit> findAllOrderDate();

    @Query("SELECT p FROM Produit p where p.enabled=true  ORDER BY p.dateCreation DESC ")
    List<Produit> findAllEnabledOrderDate();




    @Query("select  p from Produit p join p.modeles m where p.enabled=true and m.id = :id_mod" )
    List<Produit> findAllByLibelleModele(@Param("id_mod") int id_mod);

    @Query("select  p from Produit p join p.carts c where c.id is not null" )
    List<Produit> findAllByCartCmd(@Param("id_mod") int id_mod);



    @Query("select  p from Produit p join p.user u where u.user_id = :user_id and p.enabled=true")
    List<Produit> findAllByUser(@Param("user_id") long user_id);
    /*
    @Query("select  p from Produit p join p.user u join Cart c where u.username = :username and c.produit.user.username = :username and c.commandes.size<>0 ")
    List<Produit> findAllByUserInCommand(@Param("username") String username);

     */
    @Query("select  p from Produit p join p.delegation d join p.delegation.gouvernorat g join p.modeles m join p.categorie c where  g.id=:id_gouvernorat and p.enabled=true and m.marque.id=:id_marque and d.id =:id_delegation and c.id =:id_cat and c.famille.id=:id_famille and m.id=:id_modele and p.enabled=true")
    List<Produit> findAllFilter1(@Param("id_famille") long id_famille, @Param("id_cat") int id_cat, @Param("id_marque") int id_marque, @Param("id_modele") int id_modele, @Param("id_gouvernorat") Long id_gouvernorat, @Param("id_delegation") Long id_delegation);
    /*
   filter 4 Param
     */
    @Query("select  p from Produit p join p.delegation d join p.delegation.gouvernorat g join p.modeles m join p.categorie c where  g.id=:id_gouvernorat and p.enabled=true and m.marque.id=:id_marque and d.id =:id_delegation and c.id =:id_cat ")
    List<Produit> findAllByGouvernoratAndDelegationAndMarqueAndCategorie(@Param("id_gouvernorat") Long id_gouvernorat, @Param("id_marque") int id_marque, @Param("id_delegation") Long id_delegation, @Param("id_cat") int id_cat);

    @Query("select  p from Produit p join p.modeles m join p.categorie c join p.delegation.gouvernorat g join p.delegation d where m.id = :id_mod and p.enabled=true and  c.id = :id_cat and g.id=:id_gouvernorat and d.id=:id_delegation")
    List<Produit> findAllByModeleAndCategorieAndGouvernoratAndDelegation(@Param("id_mod") int id_mod, @Param("id_cat") int id_cat,@Param("id_gouvernorat") Long id_gouvernorat,@Param("id_delegation") Long id_delegation);

     /*
    filter 3 Param
     */
//
    @Query("select  p from Produit p join p.delegation d join p.delegation.gouvernorat g join p.modeles m where  g.id=:id_gouvernorat and p.enabled=true and m.marque.id=:id_marque and d.id =:id_delegation")
    List<Produit> findAllByGouvernoratAndDelegationAndMarque(@Param("id_gouvernorat") Long id_gouvernorat,@Param("id_marque") int id_marque, @Param("id_delegation") Long id_delegation);

    @Query("select  p from Produit p join p.delegation.gouvernorat g join p.modeles m join p.categorie c where  g.id=:id_gouvernorat and p.enabled=true and m.marque.id=:id_marque and c.id =:id_cat ")
    List<Produit> findAllByGouvernoratAndMarqueAndCategorie(@Param("id_gouvernorat") Long id_gouvernorat, @Param("id_marque") int id_marque, @Param("id_cat") int id_cat);

    @Query("select  p from Produit p join p.modeles m join p.categorie c join p.delegation.gouvernorat g where m.id = :id_mod and p.enabled=true and  c.id = :id_cat and g.id=:id_gouvernorat ")
    List<Produit> findAllByModeleAndCategorieAndGouvernorat(@Param("id_mod") int id_mod, @Param("id_cat") int id_cat,@Param("id_gouvernorat") Long id_gouvernorat);

    @Query("select  p from Produit p join p.modeles m join p.delegation.gouvernorat g where m.marque.id=:id_marque and p.enabled=true and m.id = :id_mod and g.id=:id_gouvernorat ")
    List<Produit> findAllByModeleGouvernorat(@Param("id_marque") int id_marque, @Param("id_mod") int id_mod,@Param("id_gouvernorat") Long id_gouvernorat);

    @Query("select  p from Produit p join p.modeles m join p.categorie c join  p.delegation d where m.id = :id_mod and c.id = :id_cat and d.id=:id_delegation and p.enabled=true")
    List<Produit> findAllByModeleAndCategorieAndDelegation(@Param("id_mod") int id_mod, @Param("id_cat") int id_cat,@Param("id_delegation") Long id_delegation);

    @Query("select  p from Produit p join p.modeles m join p.categorie c where m.id = :id_mod and c.id = :id_cat  and m.marque.id=:id_marque and p.enabled=true")
    List<Produit> findAllByModeleAndCategorieAndMarque(@Param("id_mod") int id_mod, @Param("id_cat") int id_cat,@Param("id_marque") int id_marque);


     /*
   filter 2 Param
     */
     @Query("select  p from Produit p   join p.modeles m where  m.id=:id_modele and m.marque.id=:id_marque and p.enabled=true")
     List<Produit> findAllByMarqueModele(@Param("id_marque") int id_marque, @Param("id_modele") int id_modele);

  

    @Query("select  p from Produit p join p.delegation.gouvernorat g join p.categorie c where  g.id=:id_gouvernorat and c.id=:id_cat and p.enabled=true")
    List<Produit> findAllByGouvernoratAndCategorie(@Param("id_gouvernorat") Long id_gouvernorat, @Param("id_cat") int id_cat);

    @Query("select  p from Produit p join p.delegation.gouvernorat g join p.delegation d where  g.id=:id_gouvernorat and d.id=:id_delegation and p.enabled=true")
    List<Produit> findAllByGouvernoratAndDelegation(@Param("id_gouvernorat") Long id_gouvernorat,@Param("id_delegation") Long id_delegation);

    @Query("select  p from Produit p join p.delegation d join p.categorie c where  d.id=:id_delegation and c.id=:id_cat and p.enabled=true")
    List<Produit> findAllByDelegationAndCategorie(@Param("id_delegation") Long id_delegation, @Param("id_cat") int id_cat);

    @Query("select  p from Produit p join p.delegation d join p.modeles m where  d.id=:id_delegation and m.id=:id_mod and p.enabled=true")
    List<Produit> findAllByDelegationAndModeles(@Param("id_delegation") Long id_delegation, @Param("id_mod") int id_cat);

    @Query("select  p from Produit p join p.delegation.gouvernorat g join p.modeles m where  g.id=:id_gouvernorat and m.id=:id_mod and p.enabled=true")
    List<Produit> findAllByGouvernoratAndModeles(@Param("id_gouvernorat") Long id_gouvernorat, @Param("id_mod") int id_cat);

    @Query("select  p from Produit p join p.modeles m join p.categorie c where  m.marque.id=:id_marque and c.id=:id_cat and p.enabled=true")
    List<Produit> findAllByMarqueAndCategorie(@Param("id_marque") int id_marque,@Param("id_cat") int id_cat);

    @Query("select  p from Produit p join p.delegation.gouvernorat g join p.modeles m where  g.id=:id_gouvernorat and m.marque.id=:id_marque and p.enabled=true")
    List<Produit> findAllByGouvernoratAndMarque(@Param("id_gouvernorat") Long id_gouvernorat,@Param("id_marque") int id_marque);

     /*
   filter 1 Param
     */

    @Query("select  p from Produit p join p.delegation.gouvernorat g where  g.id=:id_gouvernorat and p.enabled=true")
    List<Produit> findAllByGouvernorat(@Param("id_gouvernorat") Long id_gouvernorat);

    @Query("select  p from Produit p join p.modeles m where  m.marque.id=:id_marque and p.enabled=true")
    List<Produit> findAllByMarque(@Param("id_marque") int id_marque);
//
    @Query("select  p from Produit p join p.categorie m where  m.famille.id=:id_famille and p.enabled=true")
    List<Produit> findAllByfamille(@Param("id_famille") Long id_famille);

    @Query("select  p from Produit p join p.categorie m where  m.famille.id=:id_famille and m.id=:id_cat and p.enabled=true")
    List<Produit> findAllByfamilleAndCat(@Param("id_famille") Long id_famille,@Param("id_cat")int id_cat);
    @Query("select  p from Produit p join p.categorie m join p.modeles mo where  m.famille.id=:id_famille and m.id=:id_cat and mo.marque.id =:id_marque and p.enabled=true")
    List<Produit> findAllByfamilleAndCatAndMarque(@Param("id_famille") Long id_famille,@Param("id_cat")int id_cat,@Param("id_marque")int id_marque);
    @Query("select  p from Produit p join p.categorie m join p.modeles mo where  m.famille.id=:id_famille and m.id=:id_cat and mo.marque.id =:id_marque and mo.id=:id_modele and p.enabled=true")
    List<Produit> findAllByfamilleCatMarqueModele(@Param("id_famille") Long id_famille,@Param("id_cat")int id_cat,@Param("id_marque")int id_marque,@Param("id_modele")int id_modele);

    @Query("select  p from Produit p join p.categorie m join p.modeles mo join p.delegation d where  m.famille.id=:id_famille and m.id=:id_cat and mo.marque.id =:id_marque and mo.id=:id_modele and d.gouvernorat.id=:id_gov and p.enabled=true")
    List<Produit> findAllByfamilleCatMarqueModeleG(@Param("id_famille") Long id_famille,@Param("id_cat")int id_cat,@Param("id_marque")int id_marque,@Param("id_modele")int id_modele,@Param("id_gov")Long id_gov);

    @Query("select  p from Produit p join p.categorie m  join p.delegation d where  m.famille.id=:id_famille  and d.gouvernorat.id=:id_gov and p.enabled=true")
    List<Produit> findAllByfG(@Param("id_famille") Long id_famille,@Param("id_gov")Long id_gov);

    @Query("select  p from Produit p join p.categorie m join p.modeles mo join p.delegation d where  m.famille.id=:id_famille  and mo.marque.id =:id_marque  and d.gouvernorat.id=:id_gov and p.enabled=true")
    List<Produit> findAllByfmg(@Param("id_famille") Long id_famille,@Param("id_marque")int id_marque,@Param("id_gov")Long id_gov);

    @Query("select  p from Produit p join p.categorie m join p.modeles mo join p.delegation d where  m.famille.id=:id_famille  and mo.marque.id =:id_marque and p.enabled=true")
    List<Produit> findAllByfm(@Param("id_famille") Long id_famille,@Param("id_marque")int id_marque);

    @Query("select  p from Produit p join p.delegation d join p.delegation.gouvernorat g join p.modeles m join p.categorie c where  g.id=:id_gouvernorat and m.marque.id=:id_marque and d.id =:id_delegation and c.famille.id=:id_famille and m.id=:id_modele and p.enabled=true")
    List<Produit> fmamogd(@Param("id_famille") long id_famille,@Param("id_marque") int id_marque, @Param("id_modele") int id_modele, @Param("id_gouvernorat") Long id_gouvernorat, @Param("id_delegation") Long id_delegation);



    @Query("select  p from Produit p where p.carburant=:carburant and p.enabled=true")
    List<Produit> findAllByCarburant(@Param("carburant") String carburant);
/*
    @Query("select  p from Produit p where p.annee=:annee")
    List<Produit> findAllByAnnee(@Param("annee") int annee);
 */
}
