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
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    private List<Destination> retrievedDestinationList;
    private List<Destination> selectedDestinationList = new ArrayList<>();
    private List<Accommodation> selectedAccommodationList = new ArrayList<>();
    private List<TransportCompany> selectedTransportCompanyList = new ArrayList<>();
    private TravelPlan currentTravelPlan;
    private Scanner scanner = new Scanner(System.in);
    private TravelSegmentDAO travelSegmentDAO = new TravelSegmentDAO();
    private TravelPlanDAO travelPlanDAO = new TravelPlanDAO();
    private DestinationDAO destinationDAO = new DestinationDAO();
    private AccomodationDAO accomodationDAO = new AccomodationDAO();
    private TransportCompanyDAO transportCompanyDAO = new TransportCompanyDAO();

    public void addTravelPlanToDatabase() {
        retrievedDestinationList = destinationDAO.retrieveDestinationList();
//        retrievedDestinationList.forEach(System.out::println);

        System.out.println(ANSI_YELLOW + "Choose destinations in order of visit (up to 10 destinations)");
        chooseDestinationByOrder();
//        selectedDestinationList.forEach(System.out::println);

        System.out.println( ANSI_YELLOW + "Choose start date and end date of travel (up to 31 days of travel)");
        chooseTravelDuration();

        System.out.println( ANSI_YELLOW + "Choose rating of trip (between 1 and 5 stars)");
        chooseAccommodationForEachDestination();
//        selectedAccommodationList.forEach(System.out::println);
        chooseTransportCompanyBetweenDestinations();
//        selectedTransportCompanyList.forEach(System.out::println);

        constructSegments();

        System.out.println(ANSI_YELLOW + "Travel plan has been added to database \n");
    }

    public void chooseDestinationByOrder() {
        int count = 1;
        for (Destination destination : retrievedDestinationList) {
            System.out.println(count + ": " + destination.getCountry() + ", " + destination.getCity() + ", " + destination.getLocation());
            count++;
        }
        int[] destinationSelection = new int[10];
        int selectionCount = 0;
        while (selectionCount != destinationSelection.length) {
            System.out.println(ANSI_GREEN + "Choose destination by number" +ANSI_PURPLE+ " " + "or" + " " +ANSI_RED+ "enter 0 to finish selection");
            int selectedDestination = scanner.nextInt();
            if (selectedDestination >= 0 && selectedDestination < count) {
                if (selectedDestination != 0) {
                    destinationSelection[selectionCount] = selectedDestination;
                    selectionCount++;
                } else {
                    break;
                }
            } else {
                System.out.println( ANSI_RED + "Selection incorrect, choose one of the corresponding numbers");
            }
        }
        System.out.println( ANSI_YELLOW+ "Selection has been finished");

        for (int index : destinationSelection) {
            if (index == 0) {
                break;
            }
            selectedDestinationList.add(retrievedDestinationList.get(index - 1));
        }
        System.out.println( ANSI_YELLOW + "Destination selection has been made");
    }

    public void chooseTravelDuration() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        int duration;
        do {
            System.out.println( ANSI_GREEN + "Enter start date: (dd/mm/yyyy)");
            LocalDate startDate = LocalDate.parse(scanner.next(), formatter);
            System.out.println(ANSI_GREEN + "Enter end date: (dd/mm/yyyy)");
            LocalDate endDate = LocalDate.parse(scanner.next(), formatter);

            duration = (int) ChronoUnit.DAYS.between(startDate, endDate);

            if (duration >= 1 && duration <= 31) {
                System.out.println( ANSI_YELLOW + "Travel duration has been chosen");
                currentTravelPlan = travelPlanDAO.insertTravelPlan(new TravelPlan(startDate, endDate, duration));
            } else {
                System.out.println( ANSI_RED + "Incorrect travel duration chosen (up to 31 days)");
            }
        } while (duration < 1 || duration > 31);
    }

    public void chooseAccommodationForEachDestination() {
        int rating;
        do {
            rating = scanner.nextInt();
            if (rating >= 1 && rating <=5) {
                for (Destination destination : selectedDestinationList) {
                    selectedAccommodationList.add(accomodationDAO.findAccomodationByDestinationAndRating(destination.getDestinationId(), rating));
                }
                System.out.println(ANSI_YELLOW + "Accommodation selection has been made");
            } else {
                System.out.println( ANSI_RED + "Incorrect rating chosen (between 1 and 5 stars)");
            }
        } while (rating < 1 || rating > 5);
    }

    public void chooseTransportCompanyBetweenDestinations() {
        for (int index = 0; index < selectedDestinationList.size() - 1; index++) {
            selectedTransportCompanyList.add(transportCompanyDAO.findTransportCompanyBetweenDestinations(selectedDestinationList.get(index).getDestinationId(),
                    selectedDestinationList.get(index + 1).getDestinationId()));
        }
    }

    public void constructSegments() {
//        TravelPlan lastTravelPlan = travelPlanDAO.findLastTravelPlanRecord();
        for (int count = 0; count < selectedDestinationList.size() - 1; count++) {
            travelSegmentDAO.insertTravelSegment(new TravelSegment(selectedDestinationList.get(count),selectedAccommodationList.get(count),
                    selectedDestinationList.get(count + 1), selectedAccommodationList.get(count + 1), selectedTransportCompanyList.get(count),
                    currentTravelPlan));
        }
    }

}
