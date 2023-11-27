package unasat.sem5.orm.hibernate.application;

import unasat.sem5.orm.hibernate.config.JPAConfiguration;
import unasat.sem5.orm.hibernate.services.LoginService;

public class App {

    public static void main(String[] args) {
        // Waarom Werkt die Fucking ding nog niet!!!!!!!!!!!!!!!!!!!!!!!!!!

        LoginService loginService = new LoginService();
        loginService.login();
        JPAConfiguration.shutdown();

    }
}
