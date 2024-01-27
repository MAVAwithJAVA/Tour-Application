package unasat.sem5.orm.hibernate.travelpackagefactory;

import unasat.sem5.orm.hibernate.entities.Traveler;

import java.util.List;

public interface TravelGroupCreator {

    public void addTravelGroupToDatabase(List<Traveler> travelerList);
}
