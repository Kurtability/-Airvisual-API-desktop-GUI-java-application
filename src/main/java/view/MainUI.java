package view;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.InputFacade;
import model.ModelFacade;
import model.ModelFacadeImpl;
import model.OutputFacade;
import model.database.Database;
import parser.JsonParser;

import java.io.IOException;
import java.sql.Connection;

public class MainUI extends Application {

    private Connection conn =  Database.createNewDatabase();// creating a new database


    private ModelFacade engine;
    public MainUI (InputFacade inputFacade, OutputFacade outputFacade) {
        this.engine = new ModelFacadeImpl(inputFacade,outputFacade);
    }

    private String userChoiceCountry, userChoiceState, userChoiceCity, userChoiceInfo, cachedData = null;
    private Boolean backButtonPressed = false;

    private Scene inputCountryMenu, inputStateMenu, inputCityMenu, inputResultMenu, outputSendSMSMenu;

    //extension
    private ListView<String> extensionList;
    //

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

    public void setUserChoiceInfo(String report){
        this.userChoiceInfo = report;
    }

    public String getUserChoiceInfo(){
        return this.userChoiceInfo;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        Database.connectThenCreateTable(conn);



        ///////// countryScene
        primaryStage.setTitle("IQAIR API");
        GridPane mainWindow = new GridPane();
        mainWindow.setPadding(new Insets(15));
        mainWindow.setHgap(5);
        mainWindow.setVgap(5);
        mainWindow.setAlignment(Pos.CENTER);

        inputCountryMenu = new Scene(mainWindow,600,600);

        mainWindow.add(new Label("Welcome to AirVisual"),0,0);
        mainWindow.add(new Label("Please select a country to start"),0,1);

        ComboBox countryComboBox = new ComboBox();
        countryComboBox.getItems().addAll(JsonParser.parseSupportedCountries(engine.listSupportedCountries()));

        mainWindow.add(countryComboBox,0,2);

        Button selectCountryButton = new Button("Next");
        mainWindow.add(selectCountryButton,1,2);

        //extension
        extensionList = new ListView<>();
        extensionList.setPrefHeight(70);
        extensionList.setPrefWidth(100);
        extensionList.setOrientation(Orientation.HORIZONTAL);
        //

        // handles action
        selectCountryButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                try {
                    setUserChoiceCountry((String) countryComboBox.getValue());
                    System.out.println("You chose country " + getUserChoiceCountry());


                    ///////// stateScene
                    primaryStage.setTitle("IQAIR API");
                    GridPane stateWindow = new GridPane();
                    stateWindow.setPadding(new Insets(15));
                    stateWindow.setHgap(5);
                    stateWindow.setVgap(5);
                    stateWindow.setAlignment(Pos.CENTER);

                    inputStateMenu = new Scene(stateWindow,600,600);

                    stateWindow.add(new Label("Welcome to AirVisual"),0,0);
                    stateWindow.add(new Label("Now please select a state"),0,1);

                    ComboBox stateComboBox = new ComboBox();
                    stateComboBox.getItems().addAll(
                            JsonParser.parseSupportedStates(engine.
                                    listSupportedStatesFromChosenCountry(getUserChoiceCountry())));

                    stateWindow.add(stateComboBox,0,2);

                    Button selectStateButton = new Button("Next");
                    stateWindow.add(selectStateButton,1,2);
                    primaryStage.setScene(inputStateMenu);

                    //extension
                    stateWindow.add(new Label("The list displayed blow is a list of cities that you have searched. "),0,8);
                    stateWindow.add(extensionList,0,9);
                    //


                    // handles action
                    selectStateButton.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            setUserChoiceState((String) stateComboBox.getValue());
                            System.out.println("You chose " + getUserChoiceState());


                            ///////// cityScene
                            primaryStage.setTitle("IQAIR API");
                            GridPane cityWindow = new GridPane();
                            cityWindow.setPadding(new Insets(15));
                            cityWindow.setHgap(5);
                            cityWindow.setVgap(5);
                            cityWindow.setAlignment(Pos.CENTER);

                            inputCityMenu = new Scene(cityWindow,600,600);

                            cityWindow.add(new Label("Welcome to AirVisual"),0,0);
                            cityWindow.add(new Label("Now please select a city"),0,1);

                            ComboBox cityComboBox = new ComboBox();
                            try {
                                cityComboBox.getItems().addAll(JsonParser.parseSupportedCities
                                        (engine.listSupportedCitiesFromChosenState
                                                (getUserChoiceState(),
                                                        getUserChoiceCountry())));
                            } catch (IOException | InterruptedException ioException) {
                                ioException.printStackTrace();
                                System.exit(0);
                            }

                            cityWindow.add(cityComboBox,0,2);

                            Button selectCityButton = new Button("Next");
                            cityWindow.add(selectCityButton,1,2);

                            primaryStage.setScene(inputCityMenu);
                            //extension
                            cityWindow.add(new Label("The list displayed blow is a list of cities that you have searched. "),0,8);
                            cityWindow.add(extensionList,0,9);
                            //

                            // handles action
                            selectCityButton.setOnAction(new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent event) {
                                    setUserChoiceCity(cityComboBox.getValue().toString());
                                    System.out.println("You chose " + getUserChoiceCity());



                                    // resultScene
                                    primaryStage.setTitle("IQAIR API");
                                    GridPane resultWindow = new GridPane();
                                    resultWindow.setPadding(new Insets(15));
                                    resultWindow.setHgap(5);
                                    resultWindow.setVgap(5);
                                    resultWindow.setAlignment(Pos.CENTER);

                                    inputResultMenu = new Scene(resultWindow,600,600);

                                    resultWindow.add(new Label("Welcome to AirVisual"),0,0);
                                    resultWindow.add(new Label("Your enquired live data is displayed below :)"),0,1);

                                    TextArea resultDisplay = new TextArea();
                                    resultDisplay.setEditable(false);
                                    resultDisplay.setPrefWidth(600);
                                    resultDisplay.setPrefHeight(100);
                                    resultDisplay.setWrapText(true);
                                    resultWindow.add(resultDisplay,0,2);

                                    try {
                                        resultDisplay.setText(JsonParser.parseSpecifiedCityData(
                                                engine.listSpecifiedCityDataFromChosenState
                                                        (getUserChoiceCity(),
                                                                getUserChoiceState(),
                                                                getUserChoiceCountry())).toString());
                                    } catch (IOException | InterruptedException ioException) {
                                        ioException.printStackTrace();
                                    }


                                    Button sendSMSButton = new Button("Send SMS");
                                    resultWindow.add(sendSMSButton,0,5);

                                    Button useCachedDataButton = new Button("Show Cached Data From Last Use");
                                    resultWindow.add(useCachedDataButton,0,6);


                                    Button backToCountryMenuButton = new Button("Back");
                                    resultWindow.add(backToCountryMenuButton,0,7);
                                    backToCountryMenuButton.setOnAction(new EventHandler<ActionEvent>() {
                                        @Override
                                        public void handle(ActionEvent event) {
                                            primaryStage.setScene(inputCountryMenu);
                                            backButtonPressed = true;
                                        }
                                    });


                                    primaryStage.setScene(inputResultMenu);


                                    /////////// caching the data
                                    try {
                                        setUserChoiceInfo(engine.listSpecifiedCityDataFromChosenState
                                                (getUserChoiceCity(),
                                                        getUserChoiceState(),
                                                        getUserChoiceCountry()));
                                    } catch (IOException ioException) {
                                        ioException.printStackTrace();
                                    } catch (InterruptedException interruptedException) {
                                        interruptedException.printStackTrace();
                                    }
                                    Database.insertData(conn,getUserChoiceCity(),getUserChoiceState(),getUserChoiceCountry(),getUserChoiceInfo());
                                    /////////// caching the data

                                    resultWindow.add(new Label("The cached data is displayed below :)"),0,3);
                                    TextArea cacheDisplay = new TextArea();
                                    cacheDisplay.setEditable(false);
                                    cacheDisplay.setPrefWidth(600);
                                    cacheDisplay.setPrefHeight(100);
                                    cacheDisplay.setWrapText(true);
                                    resultWindow.add(cacheDisplay,0,4);

                                    useCachedDataButton.setOnAction(new EventHandler<ActionEvent>() {
                                        @Override
                                        public void handle(ActionEvent event) {

                                            if (!backButtonPressed){
                                                cacheDisplay.setText("ops, no cached data to show. If you click 'BACK' now or click 'BACK' after you sendSMS, and choose the same city next time, the cached data will be ready to displayed");
                                            }else{
                                                cachedData = Database.queryData(conn);
                                                cacheDisplay.setText(cachedData);
                                            }
                                        }
                                    });
                                    /////////// caching the data


                                    //extension
                                    resultWindow.add(new Label("The list displayed blow is a list of cities that you have searched. "),0,8);
                                    resultWindow.add(extensionList,0,9);
                                    if (extensionList.getItems().size()<3){
                                        extensionList.getItems().add(getUserChoiceCity());
                                    }else{

                                        Stage popUpWindow = new Stage();

                                        popUpWindow.initModality(Modality.APPLICATION_MODAL);
                                        popUpWindow.setTitle("Alert box");
                                        popUpWindow.setMinWidth(360);
                                        Label label = new Label("please choose an index of the city that you want to replace");
                                        Button closeButton = new Button("Close the window");
                                        closeButton.setOnAction(e->popUpWindow.close());

                                        ComboBox<Integer> indexes = new ComboBox();
                                        indexes.getItems().addAll(0,1,2);


                                        VBox layout = new VBox(10);
                                        layout.getChildren().addAll(label, indexes, closeButton);
                                        layout.setAlignment(Pos.CENTER);

                                        Scene scene = new Scene(layout);
                                        popUpWindow.setScene(scene);
                                        popUpWindow.showAndWait();

                                        int index = indexes.getValue();
                                        extensionList.getItems().remove(index);
                                        extensionList.getItems().add(indexes.getValue(), getUserChoiceCity());

                                    }
                                    //


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

                                            outputSendSMSMenu = new Scene(SMSWindow,600,600);

                                            SMSWindow.add(new Label("Welcome to Twillo"),0,0);
                                            SMSWindow.add(new Label("Your sent SMS response is displayed below :)"),0,1);

                                            TextArea SMSDisplay = new TextArea();
                                            SMSDisplay.setEditable(false);
                                            SMSDisplay.setPrefWidth(600);
                                            SMSDisplay.setPrefHeight(100);
                                            SMSDisplay.setWrapText(true);
                                            SMSWindow.add(SMSDisplay,0,2);

                                            try {
                                                //extension
                                                String reportTail = "";
                                                for (String city : extensionList.getItems()){
                                                    if (city != getUserChoiceCity()){
                                                        reportTail += "(" + city + ") ";
                                                    }
                                                }
                                                SMSDisplay.setText(engine.sendSMS(JsonParser.parseSpecifiedCityData(
                                                        engine.listSpecifiedCityDataFromChosenState
                                                                (getUserChoiceCity(),
                                                                        getUserChoiceState(),
                                                                        getUserChoiceCountry())).toString()+ reportTail));
                                                //extension

                                            } catch (IOException | InterruptedException ioException) {
                                                ioException.printStackTrace();
                                            }
                                            primaryStage.setScene(outputSendSMSMenu);

                                            Button backToCountryMenuButton = new Button("Back");
                                            SMSWindow.add(backToCountryMenuButton,0,3);
                                            backToCountryMenuButton.setOnAction(new EventHandler<ActionEvent>() {
                                                @Override
                                                public void handle(ActionEvent event) {
                                                    backButtonPressed = true;
                                                    primaryStage.setScene(inputCountryMenu);
                                                }
                                            });

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
        primaryStage.setScene(inputCountryMenu);
        primaryStage.show();
    }
}
