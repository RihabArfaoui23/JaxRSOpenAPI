package fr.istic.taa.jaxrs;


import java.util.HashSet;
import java.util.Set;

import fr.istic.taa.jaxrs.rest.ConcertRessource;
import fr.istic.taa.jaxrs.rest.SwaggerResource;
import fr.istic.taa.jaxrs.rest.TicketRessource;
import fr.istic.taa.jaxrs.rest.UtilisateurRessource;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;
import io.swagger.v3.jaxrs2.integration.resources.OpenApiResource;

@ApplicationPath("/")
public class RestApplication extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        final Set<Class<?>> resources = new HashSet<>();

        resources.add(OpenApiResource.class);

        resources.add(TicketRessource.class);
        resources.add(ConcertRessource.class);
        resources.add(UtilisateurRessource.class);

        resources.add(SwaggerResource.class);

        return resources;
    }


}
