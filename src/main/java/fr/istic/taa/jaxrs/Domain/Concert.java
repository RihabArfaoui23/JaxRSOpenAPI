package fr.istic.taa.jaxrs.Domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


@Entity
public class Concert implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String artiste;
    private String lieu;
    private Date date;
    private String genre;
    private double prix;


    @OneToMany(mappedBy = "concert", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Ticket> tickets;


    public Concert() {}

    public Concert(String artiste, String lieu, Date date, String genre, double prix) {
        this.artiste = artiste;
        this.lieu = lieu;
        this.date = date;
        this.genre = genre;
        this.prix = prix;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getArtiste() {
        return artiste;
    }

    public void setArtiste(String artiste) {
        this.artiste = artiste;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }
}
