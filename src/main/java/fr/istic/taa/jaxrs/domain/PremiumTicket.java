package fr.istic.taa.jaxrs.Domain;

import jakarta.persistence.Entity;

@Entity
    public class PremiumTicket extends Ticket {

        private String avantages;

        public PremiumTicket() {}

        public PremiumTicket(double prix, Concert concert, Utilisateur utilisateur, String avantages) {
            super(prix, concert, utilisateur);
            this.avantages = avantages;
        }

    public String getAvantages() {
        return avantages;
    }

    public void setAvantages(String avantages) {
        this.avantages = avantages;
    }

    }

