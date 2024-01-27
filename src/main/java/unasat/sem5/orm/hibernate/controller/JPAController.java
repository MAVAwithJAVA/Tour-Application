package unasat.sem5.orm.hibernate.controller;

import unasat.sem5.orm.hibernate.config.JPAConfiguration;

import javax.persistence.EntityManager;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

public class JPAController {

    private EntityManager entityManager;

    @Path("/startJPA")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String startJPA(){
        entityManager = JPAConfiguration.getEntityManager();
        return "JPA Started";
    }
}
