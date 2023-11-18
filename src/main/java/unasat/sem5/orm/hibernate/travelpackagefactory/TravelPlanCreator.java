package unasat.sem5.orm.hibernate.travelpackagefactory;

public interface TravelPlanCreator {

    public void addTravelPlanToDatabase();

    public void chooseDestinationByOrder();

    public void chooseTravelDuration();

    public void chooseAccommodationForEachDestination();

    public void constructSegments();

    public void chooseTransportCompanyBetweenDestinations();

}
