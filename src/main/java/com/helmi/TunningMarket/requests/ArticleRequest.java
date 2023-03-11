package com.helmi.TunningMarket.requests;

import com.helmi.TunningMarket.entities.Comment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.*;
import java.util.Date;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleRequest {
    private Long id;
    private String title;
    private Date dateCreation;
    private String text;
    private String filename;
    private String user;
    private List<Comment> comments;
}
