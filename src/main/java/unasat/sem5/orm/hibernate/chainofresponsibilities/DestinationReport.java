package unasat.sem5.orm.hibernate.chainofresponsibilities;

import unasat.sem5.orm.hibernate.dao.TravelSegmentDAO;
import unasat.sem5.orm.hibernate.entities.Destination;
import unasat.sem5.orm.hibernate.entities.TravelSegment;

import java.util.List;

public class DestinationReport implements Chain {

    private Chain nextInChain;

    TravelSegmentDAO travelSegmentDAO = new TravelSegmentDAO();

    List<TravelSegment> travelSegmentList;

    @Override
    public void setNextChain(Chain nextChain) {
        this.nextInChain = nextChain;
    }

    @Override
    public void getReport(String request) {
        if (request.equals("Destination report")) {
            travelSegmentList = travelSegmentDAO.retrieveTravelSegmentByDestinationFrequency();
            System.out.println("DESTINATION REPORT");
            System.out.println("The destinations travelled to from most to least are as follows: ");
            for (TravelSegment travelSegment : travelSegmentList) {
                System.out.println(travelSegment.getDestination().getCountry() + ", " + travelSegment.getDestination().getCity() +
                        ", " + travelSegment.getDestination().getLocation());
            }
            Destination mostTravelSegmentDestination = travelSegmentList.get(travelSegmentList.size()-1).getDestination();
            Destination leastTravelSegmentDestination = travelSegmentList.get(0).getDestination();
            System.out.println();
            System.out.println("The destination most travelled to is " + mostTravelSegmentDestination.getCountry() + ", " +
                    mostTravelSegmentDestination.getCity() + ", " + mostTravelSegmentDestination.getLocation());
            System.out.println("The destination least travelled to is " + leastTravelSegmentDestination.getCountry() + ", " +
                    leastTravelSegmentDestination.getCity() + ", " + leastTravelSegmentDestination.getLocation());
        } else {
            nextInChain.getReport(request);
        }
    }

}
