package fr.istic.taa.jaxrs.Domain;


import jakarta.persistence.*;

import java.io.Serializable;

@Entity
//STRATEGY 1
// @Inheritance(strategy = InheritanceType.SINGLE_TABLE)
// Creer une seule table et ajouter une colonne qui spécifie le type fille.
//STRATEGY 2
// Créer une table pour la classe mére et une table pour la classe fille.
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "type_ticket")
public  class Ticket implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double prix;
    private boolean estUtilise;

    @ManyToOne
    private Concert concert;

    @ManyToOne
    private Utilisateur utilisateur;

    public Ticket() {
    }

    public Ticket(double prix, Concert concert, Utilisateur utilisateur) {
        this.prix = prix;
        this.concert = concert;
        this.utilisateur = utilisateur;
        this.estUtilise = false;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public boolean isEstUtilise() {
        return estUtilise;
    }

    public void setEstUtilise(boolean estUtilise) {
        this.estUtilise = estUtilise;
    }

    public Concert getConcert() {
        return concert;
    }

    public void setConcert(Concert concert) {
        this.concert = concert;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }
}
