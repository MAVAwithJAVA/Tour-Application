package unasat.sem5.orm.hibernate.chainofresponsibilities;

import unasat.sem5.orm.hibernate.dao.TravelPlanDAO;
import unasat.sem5.orm.hibernate.entities.TravelPlan;

import java.time.Month;
import java.util.*;

public class TravelPeriodReport implements Chain {
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    private Chain nextInChain;

    TravelPlanDAO travelPlanDAO = new TravelPlanDAO();

    List<TravelPlan> travelPlanList;
    Map<Month, Integer> travelPlanMonthList = new HashMap<>();

    @Override
    public void setNextChain(Chain nextChain) {
        this.nextInChain = nextChain;
    }

    @Override
    public void getReport(String request) {
        if (request.equals("Period report")) {
            System.out.println(ANSI_YELLOW+"TRAVEL PERIOD REPORT");
            travelPlanList = travelPlanDAO.retrieveTravelPlans();

            for (int index = 0; index < travelPlanList.size(); index++) {
                Month key = travelPlanList.get(index).getStartDate().getMonth();
                if (travelPlanMonthList.containsKey(key)) {
                    int count = travelPlanMonthList.get(key);
                    count++;
                    travelPlanMonthList.put(key, count);

                } else {
                    travelPlanMonthList.put(key, 1);
                }
            }

            TreeMap<Month, Integer> sorted = new TreeMap<>(travelPlanMonthList);
            Set<Map.Entry<Month, Integer>> mappings = sorted.entrySet();

            System.out.println(ANSI_YELLOW+ "The following is a list of amount of travel by month");

            for (Map.Entry<Month, Integer> val : mappings) {
                System.out.println(ANSI_GREEN+"The month " + val.getKey() + ANSI_GREEN+" is travelled " + val.getValue() + ANSI_GREEN+ " times");
            }
        } else {
            System.out.println(ANSI_RED+"Incorrect request given");
        }
    }

}
