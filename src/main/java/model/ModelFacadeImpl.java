package model;

import java.io.IOException;

public class ModelFacadeImpl implements ModelFacade {
    private InputFacade inputFacade;
    private OutputFacade outputFacade;

    public ModelFacadeImpl(InputFacade inputFacade, OutputFacade outputFacade){
        this.inputFacade = inputFacade;
        this.outputFacade = outputFacade;
    }

    @Override
    public String Input_listSupportedCountries() throws IOException, InterruptedException {
        String response = inputFacade.Input_listSupportedCountries();

        return response;

    }

    @Override
    public String Input_listSupportedStatesFromChosenCountry(String country) throws IOException, InterruptedException {
        String response = inputFacade.Input_listSupportedStatesFromChosenCountry(country);

        return response;
    }

    @Override
    public String Input_listSupportedCitiesFromChosenState(String state, String country) throws IOException, InterruptedException {
        String response = inputFacade.Input_listSupportedCitiesFromChosenState(state,country);
        return response;
    }

    @Override
    public String Input_listSpecifiedCityDataFromChosenState(String city, String state, String country) throws IOException, InterruptedException {
        String response = inputFacade.Input_listSpecifiedCityDataFromChosenState(city,state,country);
        return response;
    }

    @Override
    public String Output_sendSMS(String report) {
        return null;
    }
}
