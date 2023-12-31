package unasat.sem5.orm.hibernate.reports;

import unasat.sem5.orm.hibernate.entities.*;

import java.util.Set;

public class TextFile implements File {
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_YELLOW = "\u001B[33m";

    private Account account;

    private Set<Traveler> travelers;

    private Set<TravelSegment> travelSegments;

    private TravelPlan travelPlan;

    private TravelGroup travelGroup;

    public TextFile(TravelPackage travelPackage) {
        account = travelPackage.getAccount();
        travelGroup = travelPackage.getTravelGroup();
        travelPlan = travelPackage.getTravelPlan();
        travelers = travelPackage.getTravelGroup().getTravelers();
        travelSegments = travelPackage.getTravelPlan().getTravelSegments();
    }

    @Override
    public void readReport() {
        System.out.println(ANSI_YELLOW+"TEXT REPORT OF TRAVEL PACKAGE");
        System.out.println(ANSI_GREEN+"The following is a text report of the travel package created by user " + account.getUsername() + ".");
        System.out.println(ANSI_GREEN+"The user has booked a trip from " + travelPlan.getStartDate() + ANSI_GREEN+" until " + travelPlan.getEndDate() +
                ANSI_GREEN+" for a duration of " + travelPlan.getDuration() + ANSI_GREEN+" days.");
        if (travelers != null) {
            System.out.println(ANSI_GREEN+"The travelers on this trip will be " + getTravelerNames() + ANSI_GREEN+" for a total of " +
                    travelGroup.getTravelerCount() + ANSI_GREEN+" travelers.");
            System.out.println(ANSI_GREEN+"The locations that will be visited are " + getLocations());
        }
        System.out.println(ANSI_GREEN+"Enjoy your trip \n");
        System.out.println(ANSI_YELLOW+"----------End of report----------");

    }

    private String getTravelerNames() {
        StringBuilder namesString = new StringBuilder();
        int count = 1;
        for (Traveler traveler : travelers) {
            if (count == travelers.size()) {
                namesString.append(" and " + traveler.getFirstName() + " " + traveler.getLastName());
            } else if (count == 1){
                namesString.append(traveler.getFirstName() + " " + traveler.getLastName());
            } else {
                namesString.append(", " + traveler.getFirstName() + " " + traveler.getLastName());
            }
            count++;
        }
        return namesString.toString();
    }

    private String getLocations() {
        StringBuilder locationsString = new StringBuilder();
        int count = 1;
        for (TravelSegment travelSegment : travelSegments) {
            if (count == travelSegments.size()) {
                locationsString.append(", " + ((travelSegment.getDestination().getCity() != null) ? travelSegment.getDestination().getCity() :
                        travelSegment.getDestination().getLocation()) + ", " + travelSegment.getDestination().getCountry() +
                        " at " + travelSegment.getAccommodation().getName() + " with a " +
                        travelSegment.getAccommodation().getRating() + " star rating");
                locationsString.append(" and " + ((travelSegment.getFollowUpDestination().getCity() != null) ? travelSegment.getFollowUpDestination().getCity() :
                        travelSegment.getFollowUpDestination().getLocation()) + ", " + travelSegment.getFollowUpDestination().getCountry() +
                        " at " + travelSegment.getFollowUpAccommodation().getName() + " with a " +
                        travelSegment.getFollowUpAccommodation().getRating() + " star rating");
            } else if (count == 1){
                locationsString.append(((travelSegment.getDestination().getCity() != null) ? travelSegment.getDestination().getCity() :
                        travelSegment.getDestination().getLocation()) + ", " + travelSegment.getDestination().getCountry() +
                        " where you will be staying at " + travelSegment.getAccommodation().getName() + " with a " +
                        travelSegment.getAccommodation().getRating() + " star rating");
            } else {
                locationsString.append(", " + ((travelSegment.getDestination().getCity() != null) ? travelSegment.getDestination().getCity() :
                        travelSegment.getDestination().getLocation()) + ", " + travelSegment.getDestination().getCountry() +
                         " at " + travelSegment.getAccommodation().getName() + " with a " +
                        travelSegment.getAccommodation().getRating() + " star rating");
            }
            count++;
        }
        return locationsString.toString();
    }


}
