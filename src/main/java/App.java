import javafx.application.Application;
import model.InputFacade;
import model.Input_Offline;
import model.Input_Online;
import view.MainUI;

public class App {


    public static void main (String[] args) throws Exception {
        MainUI mainUI = new MainUI();
        InputFacade inputFacade;


        // offline version
        if((args[0]).equals("offline")){
            System.out.println("ur using offline");
            inputFacade = new Input_Offline();
        }
        // online version
        else{
            System.out.println("ur using online");
            inputFacade = new Input_Online();

        }
        mainUI.setInputFacade(inputFacade);
        Application.launch(MainUI.class,args);


    }
}
