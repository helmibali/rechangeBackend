package com.helmi.TunningMarket.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Type;


import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "users",uniqueConstraints={@UniqueConstraint(columnNames={"username"})})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long  user_id;

    @Column(name = "username",unique=true)
    private String username;


    @Column(name = "password")
    @Basic(fetch = FetchType.LAZY, optional = false)
    private String password;

    @Column(name = "enabled")
    private Boolean enabled;

    @Column(name = "nom")
    private String nom;

    @Column(name = "prenom")
    private String prenom;

    @Column(name = "telephone")
    private String telephone;

    @Column(name = "naissace")
    private Date naissance;

    @Column(name="filename")
    private String filename;

    @Column(name="photoUrl")
    private String photoUrl;

    @Column(name = "reset_password_token")
    private String resetPasswordToken;

    private String token;

    @ManyToMany(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @JoinTable(name="user_role", joinColumns = @JoinColumn(name="user_id"),
            inverseJoinColumns = @JoinColumn(name="role_id"))
    private List<Role> roles;

    @JsonIgnore
    @OneToMany
    @JoinColumn(name = "username")
    private List<Comment> comments;

    @JsonIgnore
    @OneToMany
    @JoinColumn(name = "username")
    private List<Article> articles;

    @JsonIgnore
    @OneToMany
    @JoinColumn(name = "username")
    private List<Produit> produits;

    @ManyToOne
    @JoinColumn(name = "delegation_id")
    private Delegation delegation;

    @JsonIgnore
    @OneToMany
    @JoinColumn(name = "username")
    private List<Cart> carts;

    @Lob
    @JsonIgnore
    @Type(type = "org.hibernate.type.ImageType")
    private byte[] imageData;

    public byte[] getImageData() {
        return imageData;
    }

    public void setImageData(byte[] imageData) {
        this.imageData = imageData;
    }

    public List<Cart> getCarts() {
        return carts;
    }

    public void setCarts(List<Cart> carts) {
        this.carts = carts;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public User(Long user_id, String username, String password, Boolean enabled, String nom, String prenom, String telephone, Date naissance, String filename, String photoUrl, List<Role> roles, List<Comment> comments, List<Article> articles, List<Produit> produits, Delegation delegation) {
        this.user_id = user_id;
        this.username = username;
        this.password = password;
        this.enabled = enabled;
        this.nom = nom;
        this.prenom = prenom;
        this.telephone = telephone;
        this.naissance = naissance;
        this.filename = filename;
        this.photoUrl = photoUrl;
        this.roles = roles;
        this.comments = comments;
        this.articles = articles;
        this.produits = produits;
        this.delegation = delegation;
    }

    public User(Long user_id, String username, String nom, String prenom, String telephone, Date naissance, Delegation delegation) {
        this.user_id = user_id;
        this.username = username;
        this.nom = nom;
        this.prenom = prenom;
        this.telephone = telephone;
        this.naissance = naissance;

        this.delegation = delegation;
    }

    public User(Long user_id, String username, String nom, String prenom, String telephone, Date naissance, List<Role> roles, Delegation delegation) {
        this.user_id = user_id;
        this.username = username;
        this.nom = nom;
        this.prenom = prenom;
        this.telephone = telephone;
        this.naissance = naissance;
        this.roles = roles;
        this.delegation = delegation;
    }

    public User(Long user_id, String username, String password, Boolean enabled, String nom, String prenom, String telephone, Date naissance, String filename, List<Role> roles, List<Comment> comments, List<Article> articles, List<Produit> produits, Delegation delegation) {
        this.user_id = user_id;
        this.username = username;
        this.password = password;
        this.enabled = enabled;
        this.nom = nom;
        this.prenom = prenom;
        this.telephone = telephone;
        this.naissance = naissance;
        this.filename = filename;
        this.roles = roles;
        this.comments = comments;
        this.articles = articles;
        this.produits = produits;
        this.delegation = delegation;
    }

    public User() {
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Date getNaissance() {
        return naissance;
    }

    public void setNaissance(Date naissance) {
        this.naissance = naissance;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }

    public List<Produit> getProduits() {
        return produits;
    }

    public void setProduits(List<Produit> produits) {
        this.produits = produits;
    }

    public Delegation getDelegation() {
        return delegation;
    }

    public void setDelegation(Delegation delegation) {
        this.delegation = delegation;
    }

    public User(String username) {
        this.username = username;
    }

    public String getResetPasswordToken() {
        return resetPasswordToken;
    }

    public void setResetPasswordToken(String resetPasswordToken) {
        this.resetPasswordToken = resetPasswordToken;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public User(Long user_id, String username, String password, Boolean enabled, String nom, String prenom, String telephone, Date naissance, String filename, String photoUrl, String resetPasswordToken, String token, List<Role> roles, List<Comment> comments, List<Article> articles, List<Produit> produits, Delegation delegation) {
        this.user_id = user_id;
        this.username = username;
        this.password = password;
        this.enabled = enabled;
        this.nom = nom;
        this.prenom = prenom;
        this.telephone = telephone;
        this.naissance = naissance;
        this.filename = filename;
        this.photoUrl = photoUrl;
        this.resetPasswordToken = resetPasswordToken;
        this.token = token;
        this.roles = roles;
        this.comments = comments;
        this.articles = articles;
        this.produits = produits;
        this.delegation = delegation;
    }

    public User(Long user_id, String username, String password, Boolean enabled, String nom, String prenom, String telephone, Date naissance, String filename, String photoUrl, String resetPasswordToken, String token, List<Role> roles, List<Comment> comments, List<Article> articles, List<Produit> produits, Delegation delegation, List<Cart> carts) {
        this.user_id = user_id;
        this.username = username;
        this.password = password;
        this.enabled = enabled;
        this.nom = nom;
        this.prenom = prenom;
        this.telephone = telephone;
        this.naissance = naissance;
        this.filename = filename;
        this.photoUrl = photoUrl;
        this.resetPasswordToken = resetPasswordToken;
        this.token = token;
        this.roles = roles;
        this.comments = comments;
        this.articles = articles;
        this.produits = produits;
        this.delegation = delegation;
        this.carts = carts;
    }

    public User(Long user_id, String username, String password, Boolean enabled, String nom, String prenom, String telephone, Date naissance, String filename, String photoUrl, String resetPasswordToken, String token, List<Role> roles, List<Comment> comments, List<Article> articles, List<Produit> produits, Delegation delegation, List<Cart> carts, byte[] imageData) {
        this.user_id = user_id;
        this.username = username;
        this.password = password;
        this.enabled = enabled;
        this.nom = nom;
        this.prenom = prenom;
        this.telephone = telephone;
        this.naissance = naissance;
        this.filename = filename;
        this.photoUrl = photoUrl;
        this.resetPasswordToken = resetPasswordToken;
        this.token = token;
        this.roles = roles;
        this.comments = comments;
        this.articles = articles;
        this.produits = produits;
        this.delegation = delegation;
        this.carts = carts;
        this.imageData = imageData;
    }
}
