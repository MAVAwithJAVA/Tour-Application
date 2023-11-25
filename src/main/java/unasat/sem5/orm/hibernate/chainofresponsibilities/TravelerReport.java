package unasat.sem5.orm.hibernate.chainofresponsibilities;

import unasat.sem5.orm.hibernate.dao.TravelerDAO;
import unasat.sem5.orm.hibernate.entities.Traveler;

public class TravelerReport implements Chain {
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_YELLOW = "\u001B[33m";

    private Chain nextInChain;

    TravelerDAO travelerDAO = new TravelerDAO();

    @Override
    public void setNextChain(Chain nextChain) {
        this.nextInChain = nextChain;
    }

    @Override
    public void getReport(String request) {
        if (request.equals("Traveler report")) {
            System.out.println(ANSI_YELLOW+ "TRAVELER REPORT");
            System.out.println(travelerDAO.countTravelers() + ANSI_YELLOW + "people have travelled with us");
            int count = 1;
            for (Traveler traveler : travelerDAO.findAllTravelers()) {
                System.out.println(count + ": " + traveler.getFirstName() + ", " + traveler.getLastName() + ", " +
                        traveler.getPassport() + ", " + traveler.getAge());
                count++;
            }
        } else {
            nextInChain.getReport(request);
        }
    }


}
