package unasat.sem5.orm.hibernate.controller;

import unasat.sem5.orm.hibernate.entities.Traveler;
import unasat.sem5.orm.hibernate.services.TravelGroupService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("controller")
public class TravelGroupController {


    private final TravelGroupService travelGroupService = new TravelGroupService();

    @Path("/addTravelers")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public boolean addTravelers(List<Traveler> travelerList) {
        return travelGroupService.addTravelers(travelerList);
    }

    @Path("/getTravelers")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Traveler> getTravelers() {
        return travelGroupService.getTravelers();
    }

    @Path("/putTraveler")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public boolean updateTraveler(Traveler traveler) {
        return travelGroupService.updateTraveler(traveler);
    }

    @Path("/deleteTraveler")
    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public boolean deleteTraveler(String passport) {
        return travelGroupService.deleteTraveler(passport);
    }
    }
