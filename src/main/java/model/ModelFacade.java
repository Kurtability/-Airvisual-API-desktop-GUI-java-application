package model;

import java.io.IOException;

public interface ModelFacade {

    String listSupportedCountries() throws IOException, InterruptedException;

    String listSupportedStatesFromChosenCountry(String country) throws IOException, InterruptedException;

    String listSupportedCitiesFromChosenState(String state, String country) throws IOException, InterruptedException;

    String listSpecifiedCityDataFromChosenState(String city, String state, String country) throws IOException, InterruptedException;

    String sendSMS(String reportToSend) throws IOException, InterruptedException;
}
