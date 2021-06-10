package view;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.*;
import parser.JsonParser;

import java.io.IOException;

public class MainUI extends Application {

    private ModelFacade engine;
    public MainUI (InputFacade inputFacade, OutputFacade outputFacade) {
        this.engine = new ModelFacadeImpl(inputFacade,outputFacade);
    }

    private String userChoiceCountry, userChoiceState, userChoiceCity;

    private Scene Input_countryMenu, Input_stateMenu, Input_cityMenu, resultMenu, sendSMSMenu;



    public void setUserChoiceCountry(String userChoiceCountry){
        if (userChoiceCountry.contains(" ")){
            this.userChoiceCountry = userChoiceCountry.replace(" ","-");
            if (userChoiceCountry.contains("SAR")){
                this.userChoiceCountry=this.userChoiceCountry.replace("SAR","");
            }

        }
        else {
            this.userChoiceCountry = userChoiceCountry;
        }

    }

    public String getUserChoiceCountry(){
        return this.userChoiceCountry;
    }

    public void setUserChoiceState(String userChoiceState){
        if (userChoiceState.contains(" ")){
            this.userChoiceState = userChoiceState.replace(" ","-");
            if(userChoiceState.contains("&")){
                this.userChoiceState = this.userChoiceState.replace("&","-");
            }
        }
        else {
            this.userChoiceState = userChoiceState;
        }
    }

    public String getUserChoiceState(){
        return this.userChoiceState;
    }

    public void setUserChoiceCity(String userChoiceCity){
        if (userChoiceCity.contains(" ")){
            this.userChoiceCity = userChoiceCity.replace(" ","-");

        }
        else {
            this.userChoiceCity = userChoiceCity;
        }
    }

    public String getUserChoiceCity(){
        return this.userChoiceCity;
    }




    @Override
    public void start(Stage primaryStage) throws Exception {


        ///////// countryScene
        primaryStage.setTitle("IQAIR API");
        GridPane mainWindow = new GridPane();
        mainWindow.setPadding(new Insets(15));
        mainWindow.setHgap(5);
        mainWindow.setVgap(5);
        mainWindow.setAlignment(Pos.CENTER);

        Input_countryMenu = new Scene(mainWindow,600,600);

        mainWindow.add(new Label("Welcome to AirVisual"),0,0);
        mainWindow.add(new Label("Please select a country to start"),0,1);

        ComboBox countryComboBox = new ComboBox();
        //engine.Input_listSupportedCountries --> InputInterface.Input_listSupportedCountries()
        countryComboBox.getItems().addAll(JsonParser.parseSupportedCountries(engine.Input_listSupportedCountries()));

        mainWindow.add(countryComboBox,0,2);

        Button selectCountryButton = new Button("Next");
        mainWindow.add(selectCountryButton,1,2);

        // handles action, maybe assign it to Presenter later on
        selectCountryButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                try {
                    setUserChoiceCountry((String) countryComboBox.getValue());
                    System.out.println("u chose country " + getUserChoiceCountry());


                    ///////// stateScene
                    primaryStage.setTitle("IQAIR API");
                    GridPane stateWindow = new GridPane();
                    stateWindow.setPadding(new Insets(15));
                    stateWindow.setHgap(5);
                    stateWindow.setVgap(5);
                    stateWindow.setAlignment(Pos.CENTER);

                    Input_stateMenu = new Scene(stateWindow,600,600);

                    stateWindow.add(new Label("Welcome to AirVisual"),0,0);
                    stateWindow.add(new Label("Now please select a state"),0,1);

                    ComboBox stateComboBox = new ComboBox();
                    stateComboBox.getItems().addAll(
                            JsonParser.parseSupportedStates(engine.
                                    Input_listSupportedStatesFromChosenCountry(getUserChoiceCountry())));

                    stateWindow.add(stateComboBox,0,2);

                    Button selectStateButton = new Button("Next");
                    stateWindow.add(selectStateButton,1,2);
                    primaryStage.setScene(Input_stateMenu);

                    // handles action
                    selectStateButton.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            setUserChoiceState((String) stateComboBox.getValue());
                            System.out.println("u chose " + getUserChoiceState());


                            ///////// cityScene
                            primaryStage.setTitle("IQAIR API");
                            GridPane cityWindow = new GridPane();
                            cityWindow.setPadding(new Insets(15));
                            cityWindow.setHgap(5);
                            cityWindow.setVgap(5);
                            cityWindow.setAlignment(Pos.CENTER);

                            Input_cityMenu = new Scene(cityWindow,600,600);

                            cityWindow.add(new Label("Welcome to AirVisual"),0,0);
                            cityWindow.add(new Label("Now please select a city"),0,1);

                            ComboBox cityComboBox = new ComboBox();
                            try {
                                cityComboBox.getItems().addAll(JsonParser.parseSupportedCities
                                        (engine.Input_listSupportedCitiesFromChosenState
                                                (getUserChoiceState(),
                                                        getUserChoiceCountry())));
                            } catch (IOException | InterruptedException ioException) {
                                ioException.printStackTrace();
                                System.exit(0);
                            }

                            cityWindow.add(cityComboBox,0,2);

                            Button selectCityButton = new Button("Next");
                            cityWindow.add(selectCityButton,1,2);

                            primaryStage.setScene(Input_cityMenu);
                            // handles action
                            selectCityButton.setOnAction(new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent event) {
                                    setUserChoiceCity(cityComboBox.getValue().toString());
                                    System.out.println("u chose " + getUserChoiceCity());


                                    // resultScene
                                    primaryStage.setTitle("IQAIR API");
                                    GridPane resultWindow = new GridPane();
                                    resultWindow.setPadding(new Insets(15));
                                    resultWindow.setHgap(5);
                                    resultWindow.setVgap(5);
                                    resultWindow.setAlignment(Pos.CENTER);

                                    resultMenu = new Scene(resultWindow,600,600);

                                    resultWindow.add(new Label("Welcome to AirVisual"),0,0);
                                    resultWindow.add(new Label("Your enquired data is displayed below :)"),0,1);

                                    TextArea resultDisplay = new TextArea();
                                    resultDisplay.setEditable(false);
                                    resultDisplay.setPrefWidth(600);
                                    resultDisplay.setPrefHeight(100);
                                    resultDisplay.setWrapText(true);
                                    resultWindow.add(resultDisplay,0,2);

                                    try {
                                        resultDisplay.setText(JsonParser.parseSpecifiedCityData(
                                                engine.Input_listSpecifiedCityDataFromChosenState
                                                        (getUserChoiceCity(),
                                                                getUserChoiceState(),
                                                                getUserChoiceCountry())).toString());
                                    } catch (IOException | InterruptedException ioException) {
                                        ioException.printStackTrace();
                                    }

                                    Button sendSMSButton = new Button("Send SMS");
                                    resultWindow.add(sendSMSButton,0,3);

                                    primaryStage.setScene(resultMenu);
                                    sendSMSButton.setOnAction(new EventHandler<ActionEvent>() {
                                        @Override
                                        public void handle(ActionEvent event) {

                                            // sendSMSScene
                                            primaryStage.setTitle("TWILLO API");
                                            GridPane SMSWindow = new GridPane();
                                            SMSWindow.setPadding(new Insets(15));
                                            SMSWindow.setHgap(5);
                                            SMSWindow.setVgap(5);
                                            SMSWindow.setAlignment(Pos.CENTER);

                                            sendSMSMenu = new Scene(SMSWindow,600,600);

                                            SMSWindow.add(new Label("Welcome to Twillo"),0,0);
                                            SMSWindow.add(new Label("Your sent SMS response is displayed below :)"),0,1);

                                            TextArea SMSDisplay = new TextArea();
                                            SMSDisplay.setEditable(false);
                                            SMSDisplay.setPrefWidth(600);
                                            SMSDisplay.setPrefHeight(100);
                                            SMSDisplay.setWrapText(true);
                                            SMSWindow.add(SMSDisplay,0,2);

                                            try {
                                                sendSMSButton.setDisable(true);
                                                SMSDisplay.setText(engine.Output_sendSMS(JsonParser.parseSpecifiedCityData(
                                                        engine.Input_listSpecifiedCityDataFromChosenState
                                                                (getUserChoiceCity(),
                                                                        getUserChoiceState(),
                                                                        getUserChoiceCountry())).toString()));
                                            } catch (IOException | InterruptedException ioException) {
                                                ioException.printStackTrace();
                                            }
                                            sendSMSButton.setDisable(true);
                                            primaryStage.setScene(sendSMSMenu);
                                        }
                                    });
                                }
                            });

                        }
                    });
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        });
        primaryStage.setScene(Input_countryMenu);
        primaryStage.show();
    }
}
