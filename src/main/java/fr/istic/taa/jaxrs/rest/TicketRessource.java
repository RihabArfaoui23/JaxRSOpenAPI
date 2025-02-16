package fr.istic.taa.jaxrs.rest;

import fr.istic.taa.jaxrs.Domain.Ticket;
import fr.istic.taa.jaxrs.dao.DomainDAO.TicketDao;
import fr.istic.taa.jaxrs.DTO.TicketDTO;
import fr.istic.taa.jaxrs.mapper.TicketMapper;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;
import java.util.stream.Collectors;

@Path("tickets")
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
@Consumes(MediaType.APPLICATION_JSON)
public class TicketRessource {

    private final TicketDao ticketDao = new TicketDao();

    /**
     * Récupérer un ticket par son ID.
     *
     * @param ticketId l'identifiant du ticket
     * @return le ticket sous forme de DTO ou une réponse 404 si non trouvé
     */
    @GET
    @Path("/{ticketId}")
    public Response getTicketById(@PathParam("ticketId") Long ticketId) {
        Ticket ticket = ticketDao.findOne(ticketId);
        if (ticket != null) {
            TicketDTO ticketDTO = TicketMapper.toDTO(ticket);
            return Response.ok(ticketDTO).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Ticket non trouvé")
                    .build();
        }
    }

    /**
     * Récupérer tous les tickets sous forme de DTO.
     *
     * @return la liste des tickets sous forme de DTO
     */
    @GET
    public List<TicketDTO> getTickets() {
        return ticketDao.findAll().stream()
                .map(TicketMapper::toDTO)
                .collect(Collectors.toList());
    }

    /**
     * Mettre à jour un ticket existant.
     *
     * @param ticketDTO les nouvelles données du ticket
     * @return le ticket mis à jour ou une réponse 404 si non trouvé
     */
    @PUT
    public Response mettreAJourTicket(TicketDTO ticketDTO) {
        Ticket ticket = ticketDao.findOne(ticketDTO.getId());
        if (ticket == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Ticket non trouvé")
                    .build();
        }

        // Mise à jour des valeurs du ticket existant
        ticket.setPrix(ticketDTO.getPrix());
        ticket.setEstUtilise(ticketDTO.isEstUtilise());

        Ticket updatedTicket = ticketDao.update(ticket);
        return Response.ok(TicketMapper.toDTO(updatedTicket)).build();
    }

    /**
     * Supprimer un ticket par ID.
     *
     * @param ticketId l'identifiant du ticket à supprimer
     * @return réponse HTTP 200 si succès, 404 si non trouvé
     */
    @DELETE
    @Path("/{ticketId}")
    public Response supprimerTicket(@PathParam("ticketId") Long ticketId) {
        Ticket ticket = ticketDao.findOne(ticketId);
        if (ticket != null) {
            ticketDao.delete(ticket);
            return Response.ok().entity("Ticket supprimé avec succès").build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Ticket non trouvé")
                    .build();
        }
    }

    /**
     * Ajouter un nouveau ticket.
     *
     * @param ticketDTO les données du ticket à ajouter
     * @return réponse HTTP 201 si succès
     */
    @POST
    public Response ajouterTicket(
            @Parameter(description = "Ticket à ajouter", required = true) TicketDTO ticketDTO) {

        Ticket ticket = TicketMapper.toEntity(ticketDTO);
        ticketDao.save(ticket);

        return Response.status(Response.Status.CREATED)
                .entity("Ticket ajouté avec succès")
                .build();
    }
}
