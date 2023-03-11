package com.helmi.TunningMarket.requests;

import com.helmi.TunningMarket.entities.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {
    public Long user_id;
    public String nom;
    public String prenom;
    public String username;
    public String telephone;
    public Date naissance;
    public String password;
    public String repassword;
    public String oldPassword;
    public String filename;
    public List<Role> roles;
    private Long delegation_id;
    private String photoUrl;
    private Boolean enabled;
    private String resetPasswordToken;
    private String token;

}
