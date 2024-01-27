package unasat.sem5.orm.hibernate.travelpackagefactory;

import unasat.sem5.orm.hibernate.dao.TravelGroupDAO;
import unasat.sem5.orm.hibernate.dao.TravelerDAO;
import unasat.sem5.orm.hibernate.entities.TravelGroup;
import unasat.sem5.orm.hibernate.entities.Traveler;


import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class TourGroupCreator implements TravelGroupCreator{

    private TravelGroup currentTravelGroup;
    private List<Traveler> currentTravelerList;

    private TravelerDAO travelerDAO = new TravelerDAO();
    private TravelGroupDAO travelGroupDAO = new TravelGroupDAO();

    @Override
    public void addTravelGroupToDatabase(List<Traveler> travelerList) {
        currentTravelerList = travelerList;
        currentTravelGroup = travelGroupDAO.insertTravelGroup(new TravelGroup(currentTravelerList.size()));
        for (Traveler traveler : currentTravelerList) {
            traveler.setTravelGroup(currentTravelGroup);
            travelerDAO.insertTraveler(traveler);
        }
    }
}
