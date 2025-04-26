package fr.istic.taa.jaxrs.rest;

import fr.istic.taa.jaxrs.Domain.Utilisateur;
import fr.istic.taa.jaxrs.dao.DomainDAO.UtilisateurDao;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import java.util.List;


@Path("utilisateur")
@Produces({"application/json", "application/xml"})
public class UtilisateurRessource {

    private final UtilisateurDao userDao = new UtilisateurDao();

    /**
     * Récupérer un utilisateur par son ID.
     * @param userId l'identifiant de l'utilisateur
     * @return l'utilisateur correspondant ou une réponse 404 s'il n'existe pas
     */
    @GET
    @Path("/{userId}")
    public Response getUserById(@PathParam("userId") Long userId) {
        Utilisateur utilisateur = userDao.findOne(userId);
        if (utilisateur != null) {
            return Response.ok(utilisateur).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Utilisateur non trouvé")
                    .build();
        }
    }

    /**
     * Récupérer la liste de tous les utilisateurs.
     * @return liste des utilisateurs
     */
    @GET
    public List<Utilisateur> getUsers() {
        return userDao.findAll();
    }

    /**
     * Mettre à jour un utilisateur.
     * @param utilisateur l'utilisateur à mettre à jour
     * @return l'utilisateur mis à jour
     */
    @PUT
    public Utilisateur mettreAJourUtilisateur(Utilisateur utilisateur) {
        return userDao.update(utilisateur);
    }

    /**
     * Supprimer un utilisateur.
     * @param userId l'ID de l'utilisateur à supprimer
     * @return une réponse HTTP indiquant le succès ou l'échec de la suppression
     */
    @DELETE
    @Path("/{userId}")
    public Response supprimerUtilisateur(@PathParam("userId") Long userId) {
        Utilisateur utilisateur = userDao.findOne(userId);
        if (utilisateur != null) {
            userDao.delete(utilisateur);
            return Response.ok().entity("Utilisateur supprimé avec succès").build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Utilisateur non trouvé")
                    .build();
        }
    }

    /**
     * Ajouter un nouvel utilisateur.
     * @param utilisateur l'utilisateur à ajouter
     * @return une réponse HTTP indiquant le succès de l'opération
     */
    @POST
    public Response ajouterUtilisateur(
            @Parameter(description = "Utilisateur à ajouter", required = true) Utilisateur utilisateur) {
        userDao.save(utilisateur); // or however you're saving
        return Response.status(Response.Status.CREATED)
                .entity(utilisateur) // ✅ send the full user as JSON
                .build();
    }


    @POST
    @Path("/login")
    @Consumes("application/json")
    public Response login(Utilisateur credentials) {
        Utilisateur utilisateur = userDao.findByEmail(credentials.getEmail());

        if (utilisateur != null && utilisateur.getPassword().equals(credentials.getPassword())) {
            return Response.ok(utilisateur).build(); // 💡 In production: return a token, not the full user
        } else {
            return Response.status(Response.Status.UNAUTHORIZED)
                    .entity("Email ou mot de passe incorrect")
                    .build();
        }
    }
}
