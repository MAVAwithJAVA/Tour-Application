package unasat.sem5.orm.hibernate.travelpackagefactory;

import unasat.sem5.orm.hibernate.dao.TravelGroupDAO;
import unasat.sem5.orm.hibernate.dao.TravelPackageDAO;
import unasat.sem5.orm.hibernate.dao.TravelPlanDAO;
import unasat.sem5.orm.hibernate.entities.Account;
import unasat.sem5.orm.hibernate.entities.TravelPackage;

public class TourPackageCreator implements TravelPackageCreator {

    private TravelPackage travelPackage;

    private TravelPackageDAO travelPackageDAO = new TravelPackageDAO();
    private TravelGroupDAO travelGroupDAO = new TravelGroupDAO();
    private TravelPlanDAO travelPlanDAO = new TravelPlanDAO();

    public TravelPackage addTravelPackageToDatabase(Account account) {
        System.out.println("Travel Package has been added to database");
        travelPackage = travelPackageDAO.insertTravelPackage(new TravelPackage(travelGroupDAO.findLastTravelGroupRecord(),
                travelPlanDAO.findLastTravelPlanRecord(), account));
        return travelPackage;
    }

}
