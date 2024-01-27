package unasat.sem5.orm.hibernate.services;

import unasat.sem5.orm.hibernate.dao.DestinationDAO;
import unasat.sem5.orm.hibernate.entities.Destination;
import unasat.sem5.orm.hibernate.travelpackagefactory.TourPlanCreator;

import java.util.List;

public class TravelPlanService {

    private DestinationDAO destinationDAO = new DestinationDAO();
    private TourPlanCreator tourPlanCreator = new TourPlanCreator();

    public List<Destination> getDestinations() {
        return destinationDAO.retrieveDestinationList();
    }

    public boolean addTravelPlan(TravelPlanData travelPlanData) {
        tourPlanCreator.addTravelPlanToDatabase(travelPlanData);
        return true;
    }

    public boolean updateDestination(Destination destination) {
        return destinationDAO.updateDestination(destination);
    }

    public boolean deleteDestination(Long destinationId) {
        return destinationDAO.deleteDestination(destinationId);
    }
}
