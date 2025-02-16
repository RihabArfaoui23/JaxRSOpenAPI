package fr.istic.taa.jaxrs.DTO;

public class TicketDTO {
    private Long id;
    private double prix;
    private boolean estUtilise;
    private Long concertId;
    private Long utilisateurId;

    public TicketDTO() {}

    public TicketDTO(Long id, double prix, boolean estUtilise, Long concertId, Long utilisateurId) {
        this.id = id;
        this.prix = prix;
        this.estUtilise = estUtilise;
        this.concertId = concertId;
        this.utilisateurId = utilisateurId;
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

    public Long getConcertId() {
        return concertId;
    }

    public void setConcertId(Long concertId) {
        this.concertId = concertId;
    }

    public Long getUtilisateurId() {
        return utilisateurId;
    }

    public void setUtilisateurId(Long utilisateurId) {
        this.utilisateurId = utilisateurId;
    }
}
