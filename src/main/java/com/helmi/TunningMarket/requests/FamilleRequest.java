package com.helmi.TunningMarket.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FamilleRequest {
    private Long id;
    private String libelle;
}
