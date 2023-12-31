package unasat.sem5.orm.hibernate.dao;

import unasat.sem5.orm.hibernate.config.JPAConfiguration;
import unasat.sem5.orm.hibernate.entities.TravelGroup;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

public class TravelGroupDAO {
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_YELLOW = "\u001B[33m";

    private EntityManager entityManager = JPAConfiguration.getEntityManager();

//    public TravelGroupDAO(EntityManager entityManager) {
//        this.entityManager = entityManager;
//    }

    public TravelGroup findLastTravelGroupRecord() {
        entityManager.getTransaction().begin();
        String jpql = "select t from TravelGroup t order by t.travelGroupId desc";
        TypedQuery<TravelGroup> query = entityManager.createQuery(jpql, TravelGroup.class);
        List<TravelGroup> travelGroupList = query.setMaxResults(1).getResultList();
        TravelGroup lastTravelGroup = travelGroupList.get(0);
        entityManager.getTransaction().commit();
        return lastTravelGroup;
    }

    public TravelGroup findTravelGroupByTraveler(String passport) {
//        entityManager.getTransaction().begin();
        String jpql = "select t from TravelGroup t join t.travelers tr where tr.passport = :passport";
        TypedQuery<TravelGroup> query = entityManager.createQuery(jpql, TravelGroup.class);
        List<TravelGroup> travelGroupList = query.setParameter("passport", passport).getResultList();
        TravelGroup travelGroup = null;
        if (!travelGroupList.isEmpty()) {
            travelGroup = travelGroupList.get(0);
        } else {
            System.out.println(ANSI_RED+"No travel group found");
        }
//        entityManager.getTransaction().commit();
        return travelGroup;
    }

    public List<TravelGroup> findAllTravelGroups() {
        entityManager.getTransaction().begin();
        Query query = entityManager.createQuery("select t from TravelGroup t");
        List<TravelGroup> travelGroupList = query.getResultList();
        entityManager.getTransaction().commit();
        return travelGroupList;
    }

    public List<TravelGroup> insertTravelGroupAndReturnLast(TravelGroup travelGroup) {
        entityManager.getTransaction().begin();
        entityManager.persist(travelGroup);
        TypedQuery<TravelGroup> query = entityManager.createQuery("select t from TravelGroup t order by t.travelGroupId desc", TravelGroup.class);
        List<TravelGroup> lastTravelGroup = query.getResultList();
        entityManager.getTransaction().commit();
        return lastTravelGroup;
    }

    public TravelGroup insertTravelGroup(TravelGroup travelGroup) {
        entityManager.getTransaction().begin();
        entityManager.persist(travelGroup);
        entityManager.getTransaction().commit();
//        System.out.println("Travel group has been inserted");
        return travelGroup;
    }

    public int updateTravelGroupDecreaseTravelerCount(TravelGroup travelGroup) {
//        entityManager.getTransaction().begin();
        String jpql = "update TravelGroup t set t.travelerCount = :travelerGroupUpdate where t.travelGroupId = :travelGroupId";
        Query query = entityManager.createQuery(jpql);
        query.setParameter("travelerGroupUpdate", travelGroup.getTravelerCount() - 1);
        query.setParameter("travelGroupId", travelGroup.getTravelGroupId());
        int rowsUpdated = query.executeUpdate();
        System.out.println(ANSI_YELLOW+"Travel group updated: " + rowsUpdated);
//        entityManager.getTransaction().commit();
        return rowsUpdated;
    }




}
