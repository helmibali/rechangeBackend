package com.helmi.TunningMarket.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfessionnelReq {
    private Long id;
    private String nom;
    private String prenom;
}
