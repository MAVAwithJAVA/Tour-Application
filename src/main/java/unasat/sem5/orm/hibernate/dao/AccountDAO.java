package unasat.sem5.orm.hibernate.dao;

import unasat.sem5.orm.hibernate.config.JPAConfiguration;
import unasat.sem5.orm.hibernate.entities.Account;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class AccountDAO {
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    private EntityManager entityManager = JPAConfiguration.getEntityManager();

    public boolean verifyAccount(String username, String password) {
        boolean isVerified = false;
        List<Account> accountList;
        entityManager.getTransaction().begin();
        String jpql = "select a from Account a where a.username = :username and a.password = :password";
        TypedQuery<Account> query = entityManager.createQuery(jpql, Account.class);
        accountList = query.setParameter("username", username).setParameter("password", password).getResultList();
        if (!accountList.isEmpty()) {
            isVerified = true;
        } else {
            System.out.println(ANSI_RED+"Account couldn't be verified");
        }
        entityManager.getTransaction().commit();
        return isVerified;
    }

    public Account retrieveAccount(String username, String password) {
        List<Account> accountList;
        Account account = null;
        entityManager.getTransaction().begin();
        String jpql = "select a from Account a where a.username = :username and a.password = :password";
        TypedQuery<Account> query = entityManager.createQuery(jpql, Account.class);
        accountList = query.setParameter("username", username).setParameter("password", password).getResultList();
        if (!accountList.isEmpty()) {
            account = accountList.get(0);
        } else {
            System.out.println(ANSI_RED+"Account couldn't be retrieved");
        }
        entityManager.getTransaction().commit();
        return account;
    }


}
