package fr.istic.taa.jaxrs.rest;

import fr.istic.taa.jaxrs.Domain.Concert;
import fr.istic.taa.jaxrs.dao.DomainDAO.ConcertDao;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("concerts")
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
@Consumes(MediaType.APPLICATION_JSON)
public class ConcertRessource {

    private final ConcertDao concertDao = new ConcertDao();

    /**
     * Récupérer un concert par son ID.
     *
     * @param concertId l'identifiant du concert
     * @return le concert correspondant ou une réponse 404 s'il n'existe pas
     */
    @GET
    @Path("/{concertId}")
    public Response getConcertById(@PathParam("concertId") Long concertId) {
        Concert concert = concertDao.findOne(concertId);
        if (concert != null) {
            return Response.ok(concert).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Concert non trouvé")
                    .build();
        }
    }

    @GET
    public List<Concert> getConcerts() {
        return concertDao.findAll();
    }

    @PUT
    public Concert mettreAJourConcert(Concert concert) {
        return concertDao.update(concert);
    }

    @DELETE
    @Path("/{concertId}")
    public Response supprimerConcert(@PathParam("concertId") Long concertId) {
        Concert concert = concertDao.findOne(concertId);
        if (concert != null) {
            concertDao.delete(concert);
            return Response.ok().entity("Concert supprimé avec succès").build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Concert non trouvé")
                    .build();
        }
    }

    @POST
    public Response ajouterConcert(
            @Parameter(description = "Concert à ajouter", required = true) Concert concert) {
        concertDao.save(concert);

        return Response.status(Response.Status.CREATED)
                .entity("Concert ajouté avec succès")
                .build();
    }
}
