package com.helmi.TunningMarket.repositories;

import com.helmi.TunningMarket.entities.Article;
import com.helmi.TunningMarket.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    @Query("SELECT a FROM Comment a ORDER BY a.dateComment ASC")
    List<Comment> findAllOrderDate();

    @Query("SELECT c FROM Comment c where c.article.id =:id ORDER BY c.dateComment ASC")
    List<Comment> findAllOrderDateByArticleId(@Param("id") long id);
}
