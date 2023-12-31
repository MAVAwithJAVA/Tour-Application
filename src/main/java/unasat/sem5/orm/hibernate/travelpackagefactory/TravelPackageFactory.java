package unasat.sem5.orm.hibernate.travelpackagefactory;

public class TravelPackageFactory {

    public static TravelGroupCreator createTravelGroup(int type) {
        TravelGroupCreator travelGroupCreator = null;
        switch (type) {
            case 1:
                travelGroupCreator = new TourGroupCreator();
                break;
//            case 2:
//                travelGroupCreator = new BudgetGroupCreator();
//                break;
        }
        return travelGroupCreator;
    }

    public static TravelPlanCreator createTravelPlan(int type) {
        TravelPlanCreator travelPlanCreator = null;
        switch (type) {
            case 1:
                travelPlanCreator = new TourPlanCreator();
                break;
//            case 2:
//                travelPlanCreator = new BudgetPlanCreator();
//                break;
        }
        return travelPlanCreator;
    }

    public static TravelPackageCreator createTravelPackage(int type) {
        TravelPackageCreator travelPackageCreator = null;
        switch (type) {
            case 1:
                travelPackageCreator = new TourPackageCreator();
                break;
//            case 2:
//                travelPackageCreator = new BudgetPackageCreator();
//                break;
        }
        return travelPackageCreator;
    }

}
