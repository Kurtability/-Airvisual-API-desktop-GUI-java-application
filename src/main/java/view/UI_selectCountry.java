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
import model.InputFacade;
import model.Input_Offline;
import model.Input_Online;
import presenter.JsonParser;

import java.io.IOException;

public class UI_selectCountry extends Application {

    public static InputFacade Input_Interface;


    private String userChoiceCountry, userChoiceState, userChoiceCity;
    Scene Input_countryMenu, Input_stateMenu, Input_cityMenu, resultMenu;

    public void setInputFacade(InputFacade facade){
        this.Input_Interface = facade;
    }

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
        //!!!
        // not working yet because i havent implemented get http
        //!!!
        countryComboBox.getItems().addAll(JsonParser.parseSupportedCountries(Input_Interface.Input_listSupportedCountries()));

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
                            JsonParser.parseSupportedStates(Input_Interface.
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
                                        (Input_Interface.Input_listSupportedCitiesFromChosenState
                                                (getUserChoiceState(),
                                                        getUserChoiceCountry())));
                            } catch (IOException ioException) {
                                ioException.printStackTrace();
                            } catch (InterruptedException interruptedException) {
                                interruptedException.printStackTrace();
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
                                    //System.out.println("u chose " + getUserChoiceCity());


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
                                                Input_Interface.Input_listSpecifiedCityDataFromChosenState
                                                        (getUserChoiceCity(),
                                                                getUserChoiceState(),
                                                                getUserChoiceCountry())).toString());
                                    } catch (IOException ioException) {
                                        ioException.printStackTrace();
                                    } catch (InterruptedException interruptedException) {
                                        interruptedException.printStackTrace();
                                    }catch (IllegalArgumentException e){
                                        // GOTTA HANDLE THIS LATER ON !!!!!!!!!
                                        System.out.println("the chosen city does not have active stations");
                                    }

                                    Button sendSMSButton = new Button("Send SMS");
                                    resultWindow.add(sendSMSButton,0,3);

                                    primaryStage.setScene(resultMenu);
                                }
                            });

                        }
                    });
                } catch (Exception exception) {
                    exception.printStackTrace();
                    System.out.println("the chosen country either does not exist in the current database or it does not have active stations");
                }
            }
        });
        primaryStage.setScene(Input_countryMenu);
        primaryStage.show();
    }
}
