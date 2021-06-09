import javafx.application.Application;
import model.*;
import view.MainUI;

public class App {


    public static void main (String[] args) throws Exception {
        MainUI mainUI = new MainUI();
        InputFacade inputFacade;
        OutputFacade outputFacade;


        // offline version
        if((args[0]).equals("offline")){
            System.out.println("you are using the offline input api");
            inputFacade = new Input_Offline();

            if(args[1].equals("offline")){
                outputFacade = new Output_Offline();
                System.out.println("you are using the offline output api");
            }
            else{
                outputFacade = new Output_Online();
                System.out.println("your are using the online output api");
            }
        }

        // online version
        else{
            System.out.println("you are using the online input api");
            inputFacade = new Input_Online();

            if(args[1].equals("offline")){
                outputFacade = new Output_Offline();
                System.out.println("you are using the offline output api");
            }
            else{
                outputFacade = new Output_Online();
                System.out.println("you are using the online output api");
            }
        }
        mainUI.setInputFacade(inputFacade);
        mainUI.setOutputFacade(outputFacade);
        Application.launch(MainUI.class,args);


    }
}
