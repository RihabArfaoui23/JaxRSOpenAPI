package fr.istic.taa.jaxrs.mapper;

import fr.istic.taa.jaxrs.DTO.TicketDTO;
import fr.istic.taa.jaxrs.Domain.Ticket;

public class TicketMapper {
    private static final ConcertDao concertDao = new ConcertDao();
    private static final UtilisateurDao utilisateurDao = new UtilisateurDao();

    public static TicketDTO toDTO(Ticket ticket) {
        return new TicketDTO(
                ticket.getId(),
                ticket.getPrix(),
                ticket.isEstUtilise(),
                ticket.getConcert() != null ? ticket.getConcert().getId() : null,
                ticket.getUtilisateur() != null ? ticket.getUtilisateur().getId() : null
        );
    }

    public static Ticket toEntity(TicketDTO ticketDTO) {
        Ticket ticket = new Ticket();
        ticket.setId(ticketDTO.getId());
        ticket.setPrix(ticketDTO.getPrix());
        ticket.setEstUtilise(ticketDTO.isEstUtilise());
        if (ticketDTO.getConcertId() != null) {
            Concert concert = concertDao.findOne(ticketDTO.getConcertId());
            ticket.setConcert(concert);
        }

        if (ticketDTO.getUtilisateurId() != null) {
            Utilisateur utilisateur = utilisateurDao.findOne(ticketDTO.getUtilisateurId());
            ticket.setUtilisateur(utilisateur);
        }
        return ticket;
    }
}
