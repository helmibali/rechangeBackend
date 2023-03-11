package com.helmi.TunningMarket.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentRequest {
    private Long id;
    private String texte;
    private Date dateComment;
    private Long parentId;
    private Long article;
    private String user;
}
