package unasat.sem5.orm.hibernate.application;


import unasat.sem5.orm.hibernate.services.LoginService;

@Deprecated
public class App {

    public static void main(String[] args) {
        // Waarom Werkt die Fucking ding nog niet!!!!!!!!!!!!!!!!!!!!!!!!!!

        LoginService loginService = new LoginService();
        loginService.login();

    }
}
