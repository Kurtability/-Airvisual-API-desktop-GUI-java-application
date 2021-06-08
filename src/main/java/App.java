import javafx.application.Application;
import model.InputFacade;
import model.Input_Offline;
import model.Input_Online;
import view.UI_selectCountry;

public class App {


    public static void main (String[] args) throws Exception {
        UI_selectCountry mainUI = new UI_selectCountry();
        InputFacade inputFacade;


        // offline version
        if((args[0]).equals("offline")){
            System.out.println("ur using offline");
            inputFacade = new Input_Offline();
            mainUI.setInputFacade(inputFacade);
        }
        // online version
        else{
            System.out.println("ur using online");
            //Application.launch(UI_selectCountry.class);
            //inputFacade = new Input_Online();
            inputFacade = new Input_Online();
            mainUI.setInputFacade(inputFacade);

        }
        Application.launch(UI_selectCountry.class,args);


    }
}
