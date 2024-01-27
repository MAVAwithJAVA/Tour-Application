package unasat.sem5.orm.hibernate.travelpackagefactory;

import unasat.sem5.orm.hibernate.dao.*;
import unasat.sem5.orm.hibernate.entities.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TourPlanCreator implements TravelPlanCreator {

    private List<Destination> selectedDestinationList = new ArrayList<>();
    private List<Accommodation> selectedAccommodationList = new ArrayList<>();
    private TravelPlan currentTravelPlan;

    private TravelSegmentDAO travelSegmentDAO = new TravelSegmentDAO();
    private TravelPlanDAO travelPlanDAO = new TravelPlanDAO();
    private DestinationDAO destinationDAO = new DestinationDAO();
    private AccommodationDAO accommodationDAO = new AccommodationDAO();

    private TravelPlanData travelPlanData;

    public void addTravelPlanToDatabase(TravelPlanData travelPlanData) {
        this.travelPlanData = travelPlanData;
        System.out.println(travelPlanData);

        chooseDestinationByOrder();
        selectedDestinationList.forEach(System.out::println);

        chooseTravelDuration();

        chooseAccommodationForEachDestination();
        selectedAccommodationList.forEach(System.out::println);

        constructSegments();
    }

    public void chooseDestinationByOrder() {
        for (String index : travelPlanData.getSelectedDestinationList()) {
            selectedDestinationList.add(destinationDAO.retrieveDestinationById(Long.valueOf(index)));
        }
    }

    public void chooseTravelDuration() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate startDate = LocalDate.parse(travelPlanData.getSelectedStartDate(), formatter);
        LocalDate endDate = LocalDate.parse(travelPlanData.getSelectedEndDate(), formatter);

        currentTravelPlan = travelPlanDAO.insertTravelPlan(new TravelPlan(startDate, endDate, travelPlanData.getDuration()));
    }

    public void chooseAccommodationForEachDestination() {
        int rating = Integer.parseInt(travelPlanData.getSelectedRating());
        for (Destination destination : selectedDestinationList) {
            selectedAccommodationList.add(accommodationDAO.findAccommodationByDestinationAndRating(destination.getDestinationId(), rating));
        }
    }

    public void constructSegments() {
//        TravelPlan lastTravelPlan = travelPlanDAO.findLastTravelPlanRecord();
        for (int count = 0; count < selectedDestinationList.size() - 1; count++) {
            travelSegmentDAO.insertTravelSegment(new TravelSegment(selectedDestinationList.get(count),selectedAccommodationList.get(count),
                    selectedDestinationList.get(count + 1), selectedAccommodationList.get(count + 1), currentTravelPlan));
        }
    }

}