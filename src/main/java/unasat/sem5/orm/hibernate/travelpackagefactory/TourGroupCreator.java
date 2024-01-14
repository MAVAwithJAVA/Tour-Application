package unasat.sem5.orm.hibernate.travelpackagefactory;

import unasat.sem5.orm.hibernate.dao.TravelGroupDAO;
import unasat.sem5.orm.hibernate.dao.TravelerDAO;
import unasat.sem5.orm.hibernate.entities.TravelGroup;
import unasat.sem5.orm.hibernate.entities.Traveler;


import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class TourGroupCreator implements TravelGroupCreator {
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    private TravelGroup currentTravelGroup;
    private boolean isSuccessful;
    private Scanner scanner = new Scanner(System.in);
    private TravelerDAO travelerDAO = new TravelerDAO();
    private TravelGroupDAO travelGroupDAO = new TravelGroupDAO();
//    private RandomGenerator rg = new RandomGenerator();

    public void addTravelGroupToDatabase() {
        int travelerCount;
        while (!isSuccessful) {
            System.out.println(ANSI_GREEN + "Choose number of travelers on trip (up to 5 travelers)");
            travelerCount = scanner.nextInt();

            if (travelerCount >=1 && travelerCount <=5) {
                System.out.println(ANSI_GREEN + "Number of travelers chosen is " + travelerCount);
                currentTravelGroup = travelGroupDAO.insertTravelGroup(new TravelGroup(travelerCount));

                int travelerCountTotal = travelerCount + 1;
                Set<Traveler> travelers = new HashSet<>();

                while (travelerCount != 0) {
                    System.out.println(ANSI_GREEN + "Enter information of traveler no. " + (travelerCountTotal - travelerCount));
                    System.out.println(ANSI_GREEN + "Enter first name: ");
                    String firstName = scanner.next();
                    System.out.println( ANSI_GREEN +"Enter last name: ");
                    String lastName = scanner.next();
                    System.out.println(ANSI_GREEN + "Enter passportNumber: ");
                    String passport = scanner.next();
                    System.out.println( ANSI_GREEN + "Enter age: ");
                    int age = scanner.nextInt();
                    travelers.add(new Traveler(firstName, lastName, passport, age, currentTravelGroup));

                    //                    travelerInput();
                    travelerCount--;
                }
                TravelGroup travelGroup = new TravelGroup(travelerCount);
                travelGroup.setTravelers(travelers);
                currentTravelGroup = travelGroupDAO.insertTravelGroup(travelGroup);

                System.out.println(ANSI_YELLOW + "Travel group has been added to database \n");
                isSuccessful = true;
            } else {
                System.out.println(ANSI_RED + "Incorrect number of travelers entered \nChoose between 1 and 5");
            }
        }
    }

    public void travelerInput() {
//        System.out.println(ANSI_GREEN + "Enter first name: ");
//        String firstName = scanner.next();
//        System.out.println( ANSI_GREEN +"Enter last name: ");
//        String lastName = scanner.next();
//        System.out.println(ANSI_GREEN + "Enter passportNumber: ");
//        String passport = scanner.next();
//        System.out.println( ANSI_GREEN + "Enter age: ");
//        int age = scanner.nextInt();
////        travelerDAO.insertTraveler(new Traveler(rg.getRName(), rg.getRLName(), rg.getRPass(), rg.getRAge(), travelGroupDAO.findLastTravelGroupRecord()));
//        travelerDAO.insertTraveler(new Traveler(firstName, lastName, passport, age, currentTravelGroup));
    }

}
