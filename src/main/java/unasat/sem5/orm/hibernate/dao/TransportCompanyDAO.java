package unasat.sem5.orm.hibernate.dao;

import unasat.sem5.orm.hibernate.config.JPAConfiguration;
import unasat.sem5.orm.hibernate.entities.TransportCompany;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class TransportCompanyDAO {

    private EntityManager entityManager = JPAConfiguration.getEntityManager();;

//    public TransportCompanyDAO(EntityManager entityManager) {
//        this.entityManager = entityManager;
//    }

    public TransportCompany findTransportCompanyBetweenDestinations(Long destinationId, Long followUpDestinationId) {
        List<TransportCompany> transportCompanyList;
        TransportCompany correspondingTransportCompany = null;
        entityManager.getTransaction().begin();
        String jpql = "select t from TransportCompany t join t.destinations d where d.destinationId in (:destinationId, :followUpDestinationId) " +
                "group by t.transportCompanyId having count(t.transportCompanyId) > 1";
        TypedQuery<TransportCompany> query = entityManager.createQuery(jpql, TransportCompany.class);
        transportCompanyList = query.setParameter("destinationId", destinationId).setParameter("followUpDestinationId", followUpDestinationId).getResultList();
        if (!transportCompanyList.isEmpty()) {
            correspondingTransportCompany = transportCompanyList.get(0);
        } else {
            System.out.println("no corresponding transport company");
        }
        entityManager.getTransaction().commit();
        return correspondingTransportCompany;
    }
}
