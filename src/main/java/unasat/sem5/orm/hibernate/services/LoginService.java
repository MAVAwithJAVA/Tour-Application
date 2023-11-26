package unasat.sem5.orm.hibernate.services;

import unasat.sem5.orm.hibernate.chainofresponsibilities.AdminReport;
import unasat.sem5.orm.hibernate.chainofresponsibilities.AdminReportHandler;
import unasat.sem5.orm.hibernate.dao.AccountDAO;
import unasat.sem5.orm.hibernate.dao.TravelGroupDAO;
import unasat.sem5.orm.hibernate.dao.TravelPackageDAO;
import unasat.sem5.orm.hibernate.dao.TravelerDAO;
import unasat.sem5.orm.hibernate.entities.Account;
import unasat.sem5.orm.hibernate.entities.TravelPackage;
import unasat.sem5.orm.hibernate.entities.Traveler;
import unasat.sem5.orm.hibernate.reports.RawReport;
import unasat.sem5.orm.hibernate.reports.ReportAdapter;
import unasat.sem5.orm.hibernate.reports.TextFile;
import unasat.sem5.orm.hibernate.travelpackagefactory.TravelGroupCreator;
import unasat.sem5.orm.hibernate.travelpackagefactory.TravelPackageCreator;
import unasat.sem5.orm.hibernate.travelpackagefactory.TravelPackageFactory;
import unasat.sem5.orm.hibernate.travelpackagefactory.TravelPlanCreator;

import java.util.Scanner;

public class LoginService {
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    private boolean inLogin = true;
    private boolean isSuccessful;
    private boolean inAdminSection = true;
    private boolean inUserSection = true;
    private boolean inCreateTravelPackage = true;
    private boolean inViewTravelPackageReport = true;
    private boolean inManageTravelerData = true;
    private Scanner scanner = new Scanner(System.in);
    private final AccountDAO accountDAO = new AccountDAO();
    private Account account;
    private AdminReportHandler adminReportHandler;
    private AdminReport adminReport;
    private final TravelerDAO travelerDAO = new TravelerDAO();
    private final TravelPackageDAO travelPackageDAO = new TravelPackageDAO();
    private TravelPackage latestTravelPackageFromAccount;

    public void login() {
        System.out.println(ANSI_YELLOW+"Welcome to your virtual travel agent");
        String selection;
        do {
            System.out.println(ANSI_YELLOW+"Would you like to sign in or sign up");
            System.out.println(ANSI_GREEN+"1: Sign up \n2: Sign in" +ANSI_RED+ "\n0: Exit App");
            selection = scanner.next();
            switch (selection) {
                case "1":
                    System.out.println(ANSI_RED+"Not available yet \n");
                    break;
                case "2":
                    while (!isSuccessful) {
                        System.out.println(ANSI_GREEN+"Enter username: ");
                        String username = scanner.next();
                        System.out.println(ANSI_GREEN+"Enter password: ");
                        String password = scanner.next();
                        if (accountDAO.verifyAccount(username, password)) {
                            account = accountDAO.retrieveAccount(username, password);
                            if (account.getAccountType().equals("user")) {
                                latestTravelPackageFromAccount = travelPackageDAO.retrieveLastTravelPackageByAccount(account);
                                System.out.println(ANSI_YELLOW+"You have successfully signed in \n");
                                userSection();
                            } else {
                                System.out.println(ANSI_YELLOW+"You have successfully signed in \n");
//                                adminSection();
                            }
                            isSuccessful = true;
                        } else {
                            System.out.println(ANSI_RED+"Incorrect username or password entered \n");
                            isSuccessful = true;
                        }
                    }
                    isSuccessful = false;
                    break;
                case "0":
                    inLogin = false;
                    break;
                default:
                    System.out.println(ANSI_RED+"Incorrect choice made" +ANSI_YELLOW+"\nTry again \n");
                    break;
            }
        } while (inLogin);
        System.out.println(ANSI_RED+"----------Exiting app---------- \n");
    }

//    public void adminSection() {
//        System.out.println(ANSI_YELLOW+"Welcome to the Admin section");
//        String selection;
//        do {
//            System.out.println(ANSI_GREEN+"What would you like to do");
//            System.out.println(ANSI_GREEN+"1: Handle data \n2: View admin reports" +ANSI_RED+ "\n0: Sign Out");
//            selection = scanner.next();
//            switch (selection) {
//                case "1":
//                    System.out.println(ANSI_RED+"Not available yet \n");
//                    break;
//                case "2":
//                    viewAdminReport();
//                    break;
//                case "0":
//                    inAdminSection = false;
//                    break;
//                default:
//                    System.out.println(ANSI_RED+"Incorrect choice made" +ANSI_YELLOW+ "\nTry again \n");
//                    break;
//            }
//        } while (inAdminSection);
//        System.out.println(ANSI_RED+"----------Exiting Admin Section----------\n");
//        inAdminSection = true;
//    }
//
//    public void viewAdminReport() {
//        adminReport = new AdminReport();
//        adminReportHandler = new AdminReportHandler();
//        String input;
//        int selection = -1;
//        int count;
//        do {
//            System.out.println(ANSI_YELLOW+"Which report would you like to view");
//            count = 1;
//            for (String reportType : adminReport.getReportList()) {
//                System.out.println(count + ": " + reportType);
//                count++;
//            }
//            System.out.println(ANSI_RED+"0: Cancel");
//            input = scanner.next();
//            if (isStringInt(input)) {
//                selection = Integer.parseInt(input);
//                if (selection >= 1 && selection < count) {
//                    adminReportHandler.startHandling(selection);
//                } else if (selection == 0) {
//                    break;
//                } else {
//                    System.out.println(ANSI_RED+"Incorrect choice made" +ANSI_YELLOW+ "\nTry again \n");
//                }
//            } else {
//                System.out.println(ANSI_RED+"Incorrect choice made" +ANSI_YELLOW+ "\nTry again \n");
//            }
//        } while ((selection < 0 || selection >= count));
//        System.out.println(ANSI_RED+"----------End of report----------\n");
//    }

    public void userSection() {
        System.out.println(ANSI_YELLOW+"Welcome to the User section");
        String selection;
        do {
            System.out.println(ANSI_GREEN+"What would you like to do");
            System.out.println(ANSI_GREEN+"1: Create a travel package \n2: Manage traveler data \n3: View latest travel package report" +ANSI_RED+" \n0: Sign Out");
            selection = scanner.next();
            switch (selection) {
                case "1":
                    createTravelPackage();
                    break;
                case "2":
                    manageTravelerData();
                    break;
                case "3":
                    viewTravelPackageReport();
                    break;
                case "0":
                    inUserSection = false;
                    break;
                default:
                    System.out.println(ANSI_RED+"Incorrect choice made" +ANSI_YELLOW+ "\nTry again \n");
                    break;
                }
            } while (inUserSection);
        System.out.println(ANSI_RED+"----------Exiting User Section----------\n");
        inUserSection = true;
    }

    public void createTravelPackage() {
        String selection;
        do {
            System.out.println(ANSI_YELLOW+"What type of travel would you like");
            System.out.println(ANSI_GREEN+"1: Tour" +ANSI_RED+ "\n0: Cancel");
            selection = scanner.next();
            switch (selection) {
                case "1":
                    System.out.println(ANSI_YELLOW+"You choose: Tour");
                    travelPackageCreator(selection);
                    inCreateTravelPackage = false;
                    break;
                case "2":
                    System.out.println(ANSI_GREEN+"You choose: Budget travel");
                    break;
                case "0":
                    inCreateTravelPackage = false;
                    break;
                default:
                    System.out.println(ANSI_RED+"Incorrect choice made\" +ANSI_RED+ \" \nTry again \n");
                    break;
            }
        } while (inCreateTravelPackage);
        //test entity ophaal probleem
//        System.out.println("OVERVIEW OF TRAVEL PACKAGE");
//        System.out.println(latestTravelPackageFromAccount);
//        System.out.println("Test: " + travelerDAO.findLastTravelerRecord());
        System.out.println(ANSI_YELLOW+"----------Exiting Travel Package Creator---------- \n");
        inCreateTravelPackage = true;
    }

    public void travelPackageCreator(String selection) {
        TravelGroupCreator travelGroupCreator = TravelPackageFactory.createTravelGroup(Integer.parseInt(selection));
        TravelPlanCreator travelPlanCreator = TravelPackageFactory.createTravelPlan(Integer.parseInt(selection));
        TravelPackageCreator travelPackageCreator = TravelPackageFactory.createTravelPackage(Integer.parseInt(selection));
        travelGroupCreator.addTravelGroupToDatabase();
        travelPlanCreator.addTravelPlanToDatabase();
        latestTravelPackageFromAccount = travelPackageCreator.addTravelPackageToDatabase(account);
    }


    public void manageTravelerData() {
        String selection;
        if (latestTravelPackageFromAccount != null) {
            do {
                System.out.println(ANSI_YELLOW+"What would you like to do");
                System.out.println(ANSI_GREEN+"1: Update travelers in travel package" +ANSI_RED+ "\n2: Remove travelers in travel package" +ANSI_RED+ "\n0: Cancel");
                selection = scanner.next();
                switch (selection) {
                    case "1":
                        updateTravelers();
                        break;
                    case "2":
                        deleteTraveler();
                        break;
                    case "0":
                        inManageTravelerData = false;
                        break;
                    default:
                        System.out.println(ANSI_RED+"Incorrect choice made" +ANSI_YELLOW+ "\nTry again \n");
                        break;
                }
            } while (inManageTravelerData);
        } else {
            System.out.println(ANSI_RED+"You have no travel packages yet \n");
        }
        System.out.println(ANSI_RED+"----------Exiting Traveler Data Manager----------");
        inManageTravelerData = true;
    }

    public void updateTravelers() {
        System.out.println(ANSI_YELLOW+"Enter updated information of traveler");
        System.out.println(ANSI_GREEN+"Enter first name: ");
        String firstName = scanner.next();
        System.out.println(ANSI_GREEN+"Enter last name: ");
        String lastName = scanner.next();
        System.out.println(ANSI_GREEN+"Enter passport: ");
        String passport = scanner.next();
        System.out.println(ANSI_GREEN+"Enter age: ");
        int age = scanner.nextInt();
        Traveler traveler = new Traveler(firstName, lastName, passport, age);
        travelerDAO.updateTraveler(traveler);
        System.out.println(ANSI_YELLOW+"Traveler has been updated");
    }

//    Logica For Deleting Travelers....
    public void deleteTraveler() {
        int deletionResult = 2;
        do {
            System.out.println(ANSI_YELLOW+"Enter passport of traveler to be removed");
            System.out.println(ANSI_GREEN+"Enter passport: ");
            String passport = scanner.next();
            deletionResult = travelerDAO.deleteTraveler(passport);
            if (deletionResult == 1) {
                System.out.println(ANSI_YELLOW+"Traveler has been deleted");
            } else if (deletionResult == 0) {
                System.out.println(ANSI_RED+"Traveler has not been deleted");
            } else {
                System.out.println(ANSI_RED+"Incorrect passport entered" +ANSI_RED+ "\nTry again \n");
            }
        }
        while (deletionResult != 1);
    }

//    View Travelers Report Package
    public void viewTravelPackageReport() {
        String selection;
        if (latestTravelPackageFromAccount != null) {
            do {
                System.out.println(ANSI_YELLOW+"How would you like to view your latest travel package");
                System.out.println(ANSI_GREEN+"1: Raw report \n2: Text file" +ANSI_RED+ "\n0: Cancel");
                selection = scanner.next();
                switch (selection) {
                    case "1":
                        RawReport rawReport = new RawReport(latestTravelPackageFromAccount);
                        rawReport.displayRawReport();
                        inViewTravelPackageReport = false;
                        break;
                    case "2":
                        // Design Pattern (Adapter Class Oproepen)
                        ReportAdapter reportAdapter = new ReportAdapter(new TextFile(latestTravelPackageFromAccount));
                        reportAdapter.displayRawReport();
                        inViewTravelPackageReport = false;
                        break;
                    case "0":
                        inViewTravelPackageReport = false;
                        break;
                    default:
                        System.out.println(ANSI_RED+"Incorrect choice made" +ANSI_GREEN+ "\nTry again \n");
                        break;
                }
            } while (inViewTravelPackageReport);
        } else {
            System.out.println(ANSI_RED+"You have no travel packages yet \n");
        }
        System.out.println(ANSI_RED+"----------Exiting Travel Package Viewer---------- \n");
        inViewTravelPackageReport = true;
    }

    public boolean isStringInt(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }

}
