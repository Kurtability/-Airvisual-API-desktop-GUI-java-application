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
    public String listSupportedCountries() throws IOException, InterruptedException {
        String response = inputFacade.listSupportedCountries();

        return response;

    }

    @Override
    public String listSupportedStatesFromChosenCountry(String country) throws IOException, InterruptedException {
        String response = inputFacade.listSupportedStatesFromChosenCountry(country);

        return response;
    }

    @Override
    public String listSupportedCitiesFromChosenState(String state, String country) throws IOException, InterruptedException {
        String response = inputFacade.listSupportedCitiesFromChosenState(state,country);
        return response;
    }

    @Override
    public String listSpecifiedCityDataFromChosenState(String city, String state, String country) throws IOException, InterruptedException {
        String response = inputFacade.listSpecifiedCityDataFromChosenState(city,state,country);
        return response;
    }

    @Override
    public String sendSMS(String reportToSend) throws IOException, InterruptedException {
        String response = outputFacade.sendSMS(reportToSend);
        return response;
    }


}
