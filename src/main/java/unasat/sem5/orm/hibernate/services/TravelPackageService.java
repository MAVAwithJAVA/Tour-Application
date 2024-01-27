package unasat.sem5.orm.hibernate.services;

import unasat.sem5.orm.hibernate.dao.AccountDAO;
import unasat.sem5.orm.hibernate.dao.TravelPackageDAO;
import unasat.sem5.orm.hibernate.entities.Account;
import unasat.sem5.orm.hibernate.entities.TravelPackage;
import unasat.sem5.orm.hibernate.travelpackagefactory.TourPackageCreator;

import java.util.List;

public class TravelPackageService {
    private AccountDAO accountDAO = new AccountDAO();
    private TourPackageCreator tourPackageCreator = new TourPackageCreator();
    private TravelPackageDAO travelPackageDAO = new TravelPackageDAO();

    public boolean verifyAccount(Account account) {
        return accountDAO.verifyAccount(account.getUsername(), account.getPassword());
    }

    public TravelPackage addTravelPackage(Account credentials) {
        return tourPackageCreator.addTravelPackageToDatabase(credentials);
    }

    public List<TravelPackage> getTravelPackages(Account credentials) {
        return travelPackageDAO.retrieveAllTravelPackagesByAccount(credentials);
    }

    public boolean deleteTravelPackage(Long travelPackageId) {
        return travelPackageDAO.deleteTravelPackage(travelPackageId);

    }
}
