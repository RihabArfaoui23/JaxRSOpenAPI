package fr.istic.taa.jaxrs.Domain;

import jakarta.persistence.*;

@Entity
public class Organisateur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private String email;

   // @OneToMany(mappedBy = "organisateur", cascade = CascadeType.ALL)
    //private List<Concert> concerts;

    public Organisateur() {}

    public Organisateur(String nom, String email) {
        this.nom = nom;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    /*public List<Concert> getConcerts() {
        return concerts;
    }

    public void setConcerts(List<Concert> concerts) {
        this.concerts = concerts;
    }*/
}
