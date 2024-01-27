1.  Clone this Project in your Terminal: https://github.com/MAVAwithJAVA/Tour-Application

2.  Database Setup:
    Database Name: travel_app_console

3.  Project Setup:
    run flyway-script: src/main/resources/db/migration


4.  Start Application Main:
    src/main/java/unasat/sem5/orm/hibernate/application/App.java

5. Application Username & Password:
                Shivan``& 1234ab.

***********************************************************************************************************************************************
    Korte Scenario Omtrent travel_app_console:

    Welcome Page:
    1. Sign in
    0. Exit App

    Enter username & Password:
            Shivan & 1234ab.



    Welcome to The User Section
    What would you like to do
    1: Create a travel package -> Tour -> Choose number of travelers on trip (up to 5 travelers) -> Number of travelers chosen is 1
                                                                                                    Enter information of traveler no. 1
                                                                                                    Enter first name:
                                                                                                    Enter last name:
                                                                                                    Enter passportNumber:
                                                                                                    Enter age:

                                                                                                    Choose destinations in order of visit (up to 10 destinations)
                                                                                                    1: Netherlands, Amsterdam, Amsterdam
                                                                                                    2: Germany, Berlin, Berlin
                                                                                                    3: Denmark, Copenhagen, Copenhagen
                                                                                                    4: Ireland, Dublin, Dublin
                                                                                                    5: Scotland, Edinburgh, Edinburgh
                                                                                                    6: Italy, Florence, Florence
                                                                                                    7: Switzerland, Geneva, Geneva
                                                                                                    8: Finland, Helsinki, Helsinki
                                                                                                    9: Turkey, Istanbul, Istanbul
                                                                                                    10: Israel, Jerusalem, Jerusalem
                                                                                                    Choose destination by number or enter 0 to finish selection
                                                                                                    Alleen Nummers uitkiezen Random Numbers

                                                                                                    Choose start date and end date of travel (up to 31 days of travel)
                                                                                                    Enter start date: (dd/mm/yyyy)
                                                                                                    Enter end date: (dd/mm/yyyy)

                                                                                                    Travel duration has been chosen
                                                                                                    Choose rating of trip (between 1 and 5 stars)
                                                                                                    Random Number uitkiezen 1-5

 *****************************************************************************************************************************************************************

    2: Manage Traveler data -> What would you like to do
                               1: Update travelers in travel package
                               2: Remove travelers in travel package
                               0: Cancel
 ******************************************************************************************************************************************************************

    3: View latest Travel package Report -> How would you like to view your latest travel package
                                            1: Raw report
                                            0: Cancel
 ******************************************************************************************************************************************************************

    0: Sign Out