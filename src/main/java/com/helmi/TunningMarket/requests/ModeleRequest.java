package com.helmi.TunningMarket.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ModeleRequest {
    public int id;
    public String libelleModele;
    public int marque_id;


}
