import javafx.application.Application;
import javafx.stage.Stage;
import model.*;
import org.checkerframework.checker.units.qual.C;
import view.MainUI;

import java.io.IOException;

public class App extends Application{


    public static void main (String[] args) throws Exception {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        InputFacade inputFacade;
        OutputFacade outputFacade;
        MainUI mainUI;

        // offline version

        if(getParameters().getUnnamed().get(0).equals("offline")){
            System.out.println("you are using the offline input api");
            inputFacade = new Input_Offline();

            if(getParameters().getUnnamed().get(1).equals("offline")){
                outputFacade = new Output_Offline();
                System.out.println("you are using the offline output api");
            }
            else{
                outputFacade = new Output_Online();
                System.out.println("your are using the online output api");
            }
            mainUI = new MainUI(inputFacade,outputFacade);
        }

        // online version
        else{
            System.out.println("you are using the online input api");
            inputFacade = new Input_Online();

            if(getParameters().getUnnamed().get(1).equals("offline")){
                outputFacade = new Output_Offline();
                System.out.println("you are using the offline output api");
            }
            else{
                outputFacade = new Output_Online();
                System.out.println("you are using the online output api");
            }
            mainUI = new MainUI(inputFacade,outputFacade);

        }
        mainUI.start(primaryStage);
    }

}
