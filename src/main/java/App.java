import javafx.application.Application;
import model.InputFacade;
import model.Input_Offline;
import model.Input_Online;
import view.UI_selectCountry;

public class App {


    public static void main (String[] args) throws Exception {
        InputFacade inputFacade;


        // offline version
        if((args[0]).equals("offline")){
            inputFacade = new Input_Offline();

            // launch the javaFX

            // first dummy input api function
            //System.out.println(inputFacade.Input_listSupportedCountries());

            // second dummy input api function
            //System.out.println(inputFacade.Input_listSupportedStatesFromChosenCountry("China"));


        }
        // online version
        else{
            inputFacade = new Input_Online();

            // launch the javaFX

            // parsed, first input api function
            //JsonParser.parseSupportedCountries(inputFacade.Input_listSupportedCountries());

            // parsed, second input api function
            //JsonParser.parseSupportedStates(inputFacade.Input_listSupportedStatesFromChosenCountry("China"));

            // parsed, third input api function
            //JsonParser.parseSupportedCities(inputFacade.Input_listSupportedCitiesFromChosenState("Beijing","China"));

            // parsed, last input api function
            //JsonParser.parseSpecifiedCityData(inputFacade.Input_listSpecifiedCityDataFromChosenState("Beijing","Beijing","China"));
        }
        Application.launch(UI_selectCountry.class,args);


    }
}
