import javafx.application.Application;
import javafx.stage.Stage;
import model.*;
import view.MainUI;

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
            inputFacade = new InputOffline();

            if(getParameters().getUnnamed().get(1).equals("offline")){
                outputFacade = new OutputOffline();
                System.out.println("you are using the offline output api");
            }
            else{
                outputFacade = new OutputOnline();
                System.out.println("your are using the online output api");
            }
        }

        // online version
        else{
            System.out.println("you are using the online input api");
            inputFacade = new InputOnline();

            if(getParameters().getUnnamed().get(1).equals("offline")){
                outputFacade = new OutputOffline();
                System.out.println("you are using the offline output api");
            }
            else{
                outputFacade = new OutputOnline();
                System.out.println("you are using the online output api");
            }

        }
        mainUI = new MainUI(inputFacade,outputFacade);
        mainUI.start(primaryStage);
    }

}
