package unasat.sem5.orm.hibernate.dao;

import unasat.sem5.orm.hibernate.config.JPAConfiguration;
import unasat.sem5.orm.hibernate.entities.TravelSegment;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class TravelSegmentDAO {

    private EntityManager entityManager = JPAConfiguration.getEntityManager();

    public List<TravelSegment> retrieveTravelSegmentByDestinationFrequency() {
        List<TravelSegment> travelSegmentList;
        entityManager.getTransaction().begin();
        String jpql = "select t from TravelSegment t join t.destination d group by d.destinationId order by count(d), d.destinationId";
        TypedQuery<TravelSegment> query = entityManager.createQuery(jpql, TravelSegment.class);
        travelSegmentList = query.getResultList();
        entityManager.getTransaction().commit();
        return travelSegmentList;
    }

    public TravelSegment insertTravelSegment(TravelSegment travelSegment) {
        entityManager.getTransaction().begin();
        entityManager.persist(travelSegment);
        entityManager.getTransaction().commit();
        return travelSegment;
    }

}
