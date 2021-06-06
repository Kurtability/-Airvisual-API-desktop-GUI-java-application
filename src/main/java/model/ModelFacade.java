package model;

import java.io.IOException;

public interface ModelFacade {

    String Input_listSupportedCountries() throws IOException, InterruptedException;

    String Input_listSupportedStatesFromChosenCountry(String country) throws IOException, InterruptedException;

    String Input_listSupportedCitiesFromChosenState(String state, String country) throws IOException, InterruptedException;

    String Input_listSpecifiedCityDataFromChosenState(String city, String state, String country) throws IOException, InterruptedException;

    String Output_sendSMS(String report);
}
