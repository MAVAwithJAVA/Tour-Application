package unasat.sem5.orm.hibernate.controller;

import unasat.sem5.orm.hibernate.entities.Account;
import unasat.sem5.orm.hibernate.entities.TravelPackage;
import unasat.sem5.orm.hibernate.services.TravelPackageService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("travelPackageController")
public class TravelPackageController {

    private TravelPackageService travelPackageService = new TravelPackageService();

    @Path("/verifyAccount")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public boolean verifyAccount(Account account) {
        return travelPackageService.verifyAccount(account);
    }

    @Path("/addTravelPackage")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public TravelPackage addTravelPackage(Account credentials) {
        return travelPackageService.addTravelPackage(credentials);
    }

    @Path("/getTravelPackages")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<TravelPackage> getTravelPackages(Account credentials) {
        return travelPackageService.getTravelPackages(credentials);
    }

    @Path("/deleteTravelPackage")
    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public boolean deleteTravelPackage(Long travelPackageId) {
        return travelPackageService.deleteTravelPackage(travelPackageId);
    }



}
