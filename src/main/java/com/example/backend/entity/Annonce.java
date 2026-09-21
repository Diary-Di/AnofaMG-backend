package com.example.backend.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.List;

@Entity
@Table(name = "annonce")
public class Annonce {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "num_id")
    private Integer numId;

    @Column(length = 50)
    private String titre;

    @Column(name = "type_batiment", length = 15)
    private String typeBatiment;

    @ManyToOne
    @JoinColumn(name = "boite_postal", referencedColumnName = "boite_postal")
    private Ville ville;

    @Column(length = 50)
    private String adresse;

    @Column(length = 13)
    private String contact;

    @Column(length = 25)
    private String email;

    private Integer prix;

    private Integer chambre;

    @Column(name = "salle_de_bain")
    private Integer salleDeBain;

    @Column(columnDefinition = "jsonb")
    @JdbcTypeCode(SqlTypes.JSON)
    private List<String> images;

    public Annonce() {
    }

    public Integer getNumId() {
        return numId;
    }

    public void setNumId(Integer numId) {
        this.numId = numId;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getTypeBatiment() {
        return typeBatiment;
    }

    public void setTypeBatiment(String typeBatiment) {
        this.typeBatiment = typeBatiment;
    }

    public Ville getVille() {
        return ville;
    }

    public void setVille(Ville ville) {
        this.ville = ville;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getPrix() {
        return prix;
    }

    public void setPrix(Integer prix) {
        this.prix = prix;
    }

    public Integer getChambre() {
        return chambre;
    }

    public void setChambre(Integer chambre) {
        this.chambre = chambre;
    }

    public Integer getSalleDeBain() {
        return salleDeBain;
    }

    public void setSalleDeBain(Integer salleDeBain) {
        this.salleDeBain = salleDeBain;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }
}