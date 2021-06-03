package view;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.InputFacade;
import model.Input_Online;

public class UI_login extends Application {

    // gotta refactor it later on, i think
    public static InputFacade Input_Interface;

    Scene Input_UserMenu;

    @Override
    public void start(Stage primaryStage) throws Exception {

        // gotta refactor it later on, i think
        Input_Interface= new Input_Online();


        /////////
        primaryStage.setTitle("IQAIR API");
        GridPane mainWindow = new GridPane();
        mainWindow.setPadding(new Insets(15));
        mainWindow.setHgap(5);
        mainWindow.setVgap(5);
        mainWindow.setAlignment(Pos.CENTER);

        Input_UserMenu = new Scene(mainWindow,800,800);

        mainWindow.add(new Label("API key"),0,0);
        TextField enterAPI = new TextField();
        enterAPI.setPromptText("Enter your api key");
        enterAPI.setFocusTraversable(false);
        enterAPI.setPrefWidth(600);
        mainWindow.add(enterAPI,1,0);

        Button login_but = new Button("Log in");
        mainWindow.add(login_but,0,1);

        // handles action, maybe assign it to Presenter later on



        primaryStage.setScene(Input_UserMenu);
        primaryStage.show();

    }

}
