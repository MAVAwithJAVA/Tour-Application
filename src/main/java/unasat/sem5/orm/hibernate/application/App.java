package unasat.sem5.orm.hibernate.application;

import unasat.sem5.orm.hibernate.config.JPAConfiguration;
import unasat.sem5.orm.hibernate.services.LoginService;

public class App {

    public static void main(String[] args) {
        // Waarom Werkt die Fucking ding nog niet!!!!!!!!!!!!!!!!!!!!!!!!!!

//        EntityManager entityManager = JPAConfiguration.getEntityManager();

        LoginService loginService = new LoginService();
        loginService.login();

//        TravelGroupDAO travelGroupDAO = new TravelGroupDAO();
//        System.out.println(travelGroupDAO.findAllTravelGroups());
//        System.out.println(travelGroupDAO.findTravelGroupByTraveler("389ht"));

//        TravelSegmentDAO travelSegmentDAO = new TravelSegmentDAO();
//        System.out.println(travelSegmentDAO.findAllTravelSegments());
//        System.out.println(travelSegmentDAO.retrieveTravelSegmentByDestinationFrequency());
//
//        TravelPackageDAO travelPackageDAO = new TravelPackageDAO();
//        RawReport rawReport = new RawReport(travelPackageDAO.retrieveLastTravelPackageReport());
//        rawReport.displayReport();


        JPAConfiguration.shutdown();








    }
}
