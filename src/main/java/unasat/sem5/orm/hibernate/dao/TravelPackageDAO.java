package unasat.sem5.orm.hibernate.dao;

import unasat.sem5.orm.hibernate.config.JPAConfiguration;
import unasat.sem5.orm.hibernate.entities.Account;
import unasat.sem5.orm.hibernate.entities.TravelPackage;
import unasat.sem5.orm.hibernate.reportresultset.PeriodByVisitFrequency;
import unasat.sem5.orm.hibernate.reportresultset.TravelersAgeByPeriod;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

public class TravelPackageDAO {

    private EntityManager entityManager = JPAConfiguration.getEntityManager();

//    public TravelPackageDAO(EntityManager entityManager) {
//        this.entityManager = entityManager;
//    }

    public TravelPackage retrieveLastTravelPackageByAccount(Account account) {
        entityManager.getTransaction().begin();
        String jpql = "select t from TravelPackage t where t.account.accountId = :accountId order by t.travelPackageId desc";
        TypedQuery<TravelPackage> query = entityManager.createQuery(jpql, TravelPackage.class);
        TravelPackage travelPackage = query.setParameter("accountId", account.getAccountId()).getResultList().stream().findFirst().orElse(null);
        entityManager.getTransaction().commit();
        return travelPackage;
    }

    public TravelPackage insertTravelPackage(TravelPackage travelPackage) {
        entityManager.getTransaction().begin();
        entityManager.persist(travelPackage);
        entityManager.getTransaction().commit();
//        System.out.println("Travel Package has been inserted");
        return travelPackage;
    }

    public List<TravelersAgeByPeriod> frequencyOfTravelersByAge(int year) {
        List<TravelersAgeByPeriod> resultList = new ArrayList<>();
        entityManager.getTransaction().begin();
        String jpql1 = "select new unasat.sem5.orm.hibernate.reportresultset.TravelersAgeByPeriod('1-30'," + year + " , count(t)) from TravelPackage t join t.travelGroup.travelers tgt join t.travelPlan tp where (tgt.age between 1 and 30) and year(tp.startDate) = :year";
        TypedQuery<TravelersAgeByPeriod> query1 = entityManager.createQuery(jpql1, TravelersAgeByPeriod.class);
        resultList.add(query1.setParameter("year", year).getSingleResult());

        String jpql2 = "select new unasat.sem5.orm.hibernate.reportresultset.TravelersAgeByPeriod('31-60'," + year + ", count(t)) from TravelPackage t join t.travelGroup.travelers tgt join t.travelPlan tp where (tgt.age between 31 and 60) and year(tp.startDate) = :year";
        TypedQuery<TravelersAgeByPeriod> query2 = entityManager.createQuery(jpql2, TravelersAgeByPeriod.class);
        resultList.add(query2.setParameter("year", year).getSingleResult());

        String jpql3 = "select new unasat.sem5.orm.hibernate.reportresultset.TravelersAgeByPeriod('61-100'," + year + ", count(t)) from TravelPackage t join t.travelGroup.travelers tgt join t.travelPlan tp where (tgt.age between 61 and 100) and year(tp.startDate) = :year";
        TypedQuery<TravelersAgeByPeriod> query3 = entityManager.createQuery(jpql3, TravelersAgeByPeriod.class);
        resultList.add(query3.setParameter("year", year).getSingleResult());

        entityManager.getTransaction().commit();
//        System.out.println(resultList);
        return resultList;
    }

    public List<PeriodByVisitFrequency> frequencyOfTravelersByPeriod(int period) {
        String jpqlPeriod = (period == 5) ? "year" : "quarter";
        entityManager.getTransaction().begin();
        String jpql = "select new unasat.sem5.orm.hibernate.reportresultset.PeriodByVisitFrequency(" + jpqlPeriod + "(t.travelPlan.startDate), count(t))" +
                " from TravelPackage t join t.travelGroup.travelers tgt group by " + jpqlPeriod + "(t.travelPlan.startDate)";
        TypedQuery<PeriodByVisitFrequency> query = entityManager.createQuery(jpql, PeriodByVisitFrequency.class);
        List<PeriodByVisitFrequency> resultList = query.getResultList();
        entityManager.getTransaction().commit();
//        System.out.println(resultList);
        return resultList;
    }


}
