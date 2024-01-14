package unasat.sem5.orm.hibernate.dao;

import unasat.sem5.orm.hibernate.config.JPAConfiguration;
import unasat.sem5.orm.hibernate.entities.Destination;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class DestinationDAO {

    private EntityManager entityManager = JPAConfiguration.getEntityManager();

    public List<Destination> retrieveDestinationList() {
        entityManager.getTransaction().begin();
        Query query = entityManager.createQuery("select d from Destination d");
        List<Destination> destinationList = query.getResultList();
        entityManager.getTransaction().commit();
        return destinationList;
    }

}
