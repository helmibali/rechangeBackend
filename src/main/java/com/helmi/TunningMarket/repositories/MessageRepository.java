package com.helmi.TunningMarket.repositories;

import com.helmi.TunningMarket.entities.Message;
import com.helmi.TunningMarket.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

public interface MessageRepository extends JpaRepository<Message,Long> {
    @Query("Select m  FROM Message m WHERE m.auteur.username=:username")
    List<Message> getMsgByAuteur(@Param("username")String username);

   @Query("Select  m  FROM Message m WHERE m.emiter.username=:username order by m.dateCreation asc ")
    List<Message> getMsgByEmiter(@Param("username")String username);

    @Query("Select  m  FROM Message m WHERE m.emiter.username=:username and m.vu=false order by m.dateCreation asc ")
    List<Message> getMsgByEmiterNonLus(@Param("username")String username);

    @Query("select m from Message m where m.auteur.username=:username or m.emiter.username=:username")
    List<Message> getAllMsgForUser(@Param("username")String username);

    @Query("select m from Message m where (m.auteur.username=:username1   and m.emiter.username=:username2) or (m.auteur.username=:username2   and m.emiter.username=:username1)")
    List<Message> getDiscussion(@Param("username1")String username1,@Param("username2")String username2);

    @Query("select distinct m.auteur,m.emiter from Message m where m.emiter.username=:username or m.auteur.username=:username")
    Set<User> getAllUser(@Param("username")String username);

}
