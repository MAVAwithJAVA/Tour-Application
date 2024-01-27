package unasat.sem5.orm.hibernate.services;

import unasat.sem5.orm.hibernate.dao.TravelGroupDAO;
import unasat.sem5.orm.hibernate.dao.TravelerDAO;
import unasat.sem5.orm.hibernate.entities.Traveler;
import unasat.sem5.orm.hibernate.travelpackagefactory.TourGroupCreator;

import java.util.List;

public class TravelGroupService {

    private TourGroupCreator tourGroupCreator = new TourGroupCreator();

    private TravelerDAO travelerDAO = new TravelerDAO();
    private TravelGroupDAO travelGroupDAO = new TravelGroupDAO();

    public List<Traveler> getTravelers() {
        return travelerDAO.retrieveAllTravelers();
    }

    public boolean addTravelers(List<Traveler> travelerList) {
        tourGroupCreator.addTravelGroupToDatabase(travelerList);
        return true;
    }

    public boolean updateTraveler(Traveler traveler) {
        return travelerDAO.updateTraveler(traveler);
    }

    public boolean deleteTraveler(String passport) {
        return travelerDAO.deleteTraveler(passport);
    }
}
