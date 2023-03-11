package com.helmi.TunningMarket.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DelegationRequest {
    private Long id;
    private String libelle;
    private Long gouvernorat;
}
