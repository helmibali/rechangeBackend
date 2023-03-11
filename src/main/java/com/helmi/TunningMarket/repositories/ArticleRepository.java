package com.helmi.TunningMarket.repositories;

import com.helmi.TunningMarket.entities.Article;
import com.helmi.TunningMarket.entities.Produit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ArticleRepository extends JpaRepository<Article, Long> {
    @Override
    Optional<Article> findById(Long id);

    @Query("SELECT a FROM Article a ORDER BY a.dateCreation DESC")
    List<Article> findAllOrderDate();
    @Query("SELECT a FROM Article a where a.user.username=:username ORDER BY a.dateCreation DESC")
    List<Article> findArticlesByUser(@Param("username")String username);
}
