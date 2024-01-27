package unasat.sem5.orm.hibernate;

import unasat.sem5.orm.hibernate.dao.TravelPackageDAO;
import unasat.sem5.orm.hibernate.dao.TravelPlanDAO;
import unasat.sem5.orm.hibernate.dao.TravelSegmentDAO;

public class Main {
    public static void main(String[] args) {
        TravelPackageDAO travelPackageDAO = new TravelPackageDAO();
        TravelSegmentDAO travelSegmentDAO = new TravelSegmentDAO();
        TravelPlanDAO travelPlanDAO = new TravelPlanDAO();
        AdminReportHandler adminReportHandler = new AdminReportHandler();

//        travelPackageDAO.frequencyOfDestinationVisitsByYear(2022);
//        travelPackageDAO.frequencyOfTravelersByPeriod(2);
        System.out.println(adminReportHandler.startHandling(new ReportRequest("Traveler Report", 2024, 5)));

    }
    }
}
