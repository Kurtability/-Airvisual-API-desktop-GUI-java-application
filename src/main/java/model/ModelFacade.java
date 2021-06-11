package model;

import java.io.IOException;
/**
 * The main access view for users of the engine module
 */
public interface ModelFacade {

    /**
     * calls the InputFacade to return a list of supported countries in JSON format
     * <b>Preconditions:</b><br>
     * A MainUI has been instantiated.<br>
     * <b>Postconditions:</b><br>
     * None<br>
     *
     * @return JSON format of the supported countries called by InputFacade.listSupportedCountries()
     * @throws IOException If the function fails to connect to the api
     * @throws InterruptedException If the function fails to connect to the api
     */
    String listSupportedCountries() throws IOException, InterruptedException;

    /**
     * calls the InputFacade to return a list of supported states from the chosen country in JSON format
     * <b>Preconditions:</b><br>
     * A MainUI has been instantiated.<br>
     * <b>Postconditions:</b><br>
     * None<br>
     *
     * @param country the country that the user intends to look up.<br>
     * @return JSON format of the supported states from the chosen country called by InputFacade.listSupportedStatesFromChosenCountry()
     * @throws IOException If the function fails to connect to the api
     * @throws InterruptedException If the function fails to connect to the api
     */
    String listSupportedStatesFromChosenCountry(String country) throws IOException, InterruptedException;

    /**
     * calls the InputFacade to return a list of supported cities from the chosen state and country in JSON format
     * <b>Preconditions:</b><br>
     * A MainUI has been instantiated.<br>
     * <b>Postconditions:</b><br>
     * None<br>
     *
     * @param state the state that the user intends to look up.
     * @param country the country that the user intends to look up.<br>
     * @return JSON format of the supported cities from the chosen state and country called by InputFacade.listSupportedCitiesFromChosenState()
     * @throws IOException If the function fails to connect to the api
     * @throws InterruptedException If the function fails to connect to the api
     */
    String listSupportedCitiesFromChosenState(String state, String country) throws IOException, InterruptedException;

    /**
     * calls the InputFacade to return a list of live data from from the chosen city, state and country in JSON format
     * <b>Preconditions:</b><br>
     * A MainUI has been instantiated.<br>
     * <b>Postconditions:</b><br>
     * None<br>
     *
     * @param city the city that the user intends to look up.
     * @param state the state that the user intends to look up.
     * @param country the country that the user intends to look up.<br>
     * @return JSON format of the live data from the chosen city ,state and country called by InputFacade.listSpecifiedCityDataFromChosenState()
     * @throws IOException If the function fails to connect to the api
     * @throws InterruptedException If the function fails to connect to the api
     */
    String listSpecifiedCityDataFromChosenState(String city, String state, String country) throws IOException, InterruptedException;

    /**
     * calls the OutputFacade to send the generated report as SMS
     * <b>Preconditions:</b><br>
     * A MainUI has been instantiated.<br>
     * <b>Postconditions:</b><br>
     * None<br>
     *
     * @param reportToSend the report that the user intends to send as SMS.<br>
     * @return JSON format of the response of the post request called by OutputFacade.sendSMS()
     * @throws IOException If the function fails to connect to the api
     * @throws InterruptedException If the function fails to connect to the api
     */
    String sendSMS(String reportToSend) throws IOException, InterruptedException;
}
