package model;

import java.io.IOException;
/**
 * This class provides access to the data stored in the IQAIR API, it can be online or offline
 */
public interface InputFacade{
    /**
     * calls the IQAIR api to return a list of supported countries in JSON format
     * <b>Preconditions:</b><br>
     * A ModelFacadeImpl has been instantiated.<br>
     * <b>Postconditions:</b><br>
     * None<br>
     *
     * @return JSON format of the supported countries
     * @throws IOException If the function fails to connect to the api
     * @throws InterruptedException If the function fails to connect to the api
     */
    String listSupportedCountries() throws IOException, InterruptedException;

    /**
     * calls the IQAIR api to return a list of supported states from the chosen country in JSON format
     * <b>Preconditions:</b><br>
     * A ModelFacadeImpl has been instantiated.<br>
     * <b>Postconditions:</b><br>
     * None<br>
     *
     * @param country the country that the user intends to look up.<br>
     * @return JSON format of the supported states from the chosen country
     * @throws IOException If the function fails to connect to the api
     * @throws InterruptedException If the function fails to connect to the api
     */
    String listSupportedStatesFromChosenCountry(String country) throws IOException, InterruptedException;

    /**
     * calls the IQAIR api to return a list of supported cities from the chosen state and country in JSON format
     * <b>Preconditions:</b><br>
     * A ModelFacadeImpl has been instantiated.<br>
     * <b>Postconditions:</b><br>
     * None<br>
     *
     * @param state the state that the user intends to look up.
     * @param country the country that the user intends to look up.<br>
     * @return JSON format of the supported cities from the chosen state and country
     * @throws IOException If the function fails to connect to the api
     * @throws InterruptedException If the function fails to connect to the api
     */
    String listSupportedCitiesFromChosenState(String state, String country) throws IOException, InterruptedException;

    /**
     * calls the IQAIR api to return a list of live data from from the chosen city, state and country in JSON format
     * <b>Preconditions:</b><br>
     * A ModelFacadeImpl has been instantiated.<br>
     * <b>Postconditions:</b><br>
     * None<br>
     *
     * @param city the city that the user intends to look up.
     * @param state the state that the user intends to look up.
     * @param country the country that the user intends to look up.<br>
     * @return JSON format of the live data from the chosen city ,state and country
     * @throws IOException If the function fails to connect to the api
     * @throws InterruptedException If the function fails to connect to the api
     */
    String listSpecifiedCityDataFromChosenState(String city, String state, String country) throws IOException, InterruptedException;

}
