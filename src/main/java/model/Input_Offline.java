package model;

import java.io.IOException;

public class Input_Offline implements InputFacade{

    @Override
    public String Input_listSupportedCountries() {
        String country = "{\"status\":\"success\",\"data\":[{\"country\":\"China\"}]}";


        return country;
    }

    @Override
    public String Input_listSupportedStatesFromChosenCountry(String country) throws IOException, InterruptedException {
        String state = "{\"status\":\"success\",\"data\":[{\"state\":\"Beijing\"}]}";
        return state;
    }

    @Override
    public String Input_listSupportedCitiesFromChosenState(String state, String country) throws IOException, InterruptedException {
        String city = "{\"status\":\"success\",\"data\":[{\"city\":\"Beijing\"}]}";
        return city;

    }

    @Override
    public String Input_listSpecifiedCityDataFromChosenState(String city, String state, String country) throws IOException, InterruptedException {
        String info = "{\"status\":\"success\",\"data\":{\"city\":\"Beijing\",\"state\":\"Beijing\",\"country\":\"China\",\"location\":{\"type\":\"Point\",\"coordinates\":[116.462153,39.941674]},\"current\":{\"weather\":{\"ts\":\"2021-06-06T14:00:00.000Z\",\"tp\":25,\"pr\":1006,\"hu\":47,\"ws\":3,\"wd\":120,\"ic\":\"10n\"},\"pollution\":{\"ts\":\"2021-06-06T15:00:00.000Z\",\"aqius\":70,\"mainus\":\"p2\",\"aqicn\":39,\"maincn\":\"o3\"}}}}";
        return info;
    }
}
