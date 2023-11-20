package unasat.sem5.orm.hibernate.reports;

import unasat.sem5.orm.hibernate.entities.*;

import java.util.Set;

public class RawReport implements Report {
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_YELLOW = "\u001B[33m";

    private Set<Traveler> travelers;

    private Set<TravelSegment> travelSegments;

    private TravelPlan travelPlan;

    private TravelGroup travelGroup;

    private TravelPackage travelPackage;

    public RawReport(TravelPackage travelPackage) {
        travelGroup = travelPackage.getTravelGroup();
        travelPlan = travelPackage.getTravelPlan();
        travelers = travelPackage.getTravelGroup().getTravelers();
        travelSegments = travelPackage.getTravelPlan().getTravelSegments();

        this.travelPackage = travelPackage;
    }

    @Override
    public void displayReport() {
        System.out.println(ANSI_GREEN +"RAW REPORT OF TRAVEL PACKAGE");
        System.out.println( ANSI_GREEN + "Travel group in travel package:");
        System.out.println(travelGroup);
        System.out.println( ANSI_GREEN + "Travelers in travel package:");
        System.out.println(travelers);
        System.out.println();
        System.out.println( ANSI_GREEN + "Travel plan in travel package:");
        System.out.println(travelPlan);
        System.out.println();
        System.out.println( ANSI_GREEN + "Travel segments in travel package:");
        System.out.println(travelSegments);
        System.out.println( ANSI_RED + "----------End of report----------");
    }
}
