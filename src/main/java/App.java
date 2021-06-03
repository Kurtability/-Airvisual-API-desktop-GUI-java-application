import javafx.application.Application;
import model.InputFacade;
import model.Input_Offline;
import model.Input_Online;
import view.UI_login;

public class App {
    public static void main (String[] args) throws Exception {
        InputFacade inputFacade;



        // launch the javaFX
        Application.launch(UI_login.class,args);

    }
}
