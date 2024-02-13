package com.example.StudySwap.engineering.observer;

import com.example.StudySwap.Main;
import com.example.StudySwap.engineering.ScannerSupport;
import com.example.StudySwap.engineering.observer.Printer;
import com.example.StudySwap.graphiccontroller.MessageBoxGUIController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

//classe responsabile della gestione e visualizzazione dei messaggi di eccezioni
public class ShowExceptionSupport {

    //costruttore privato per evitare istanze esterne
    private ShowExceptionSupport(){

    }

    public static void showException(String message){
        Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initStyle(StageStyle.UNDECORATED);

        //caricamento file fxml relativo al dialog box
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(Main.class.getResource("/fxml/messageBox.fxml"));
        Scene scene = null;

        //creazione scena e associazione al layout FXML caricato
        try{
            scene = new Scene(fxmlLoader.load());
        }catch(IOException e){
            Printer.printError(e.getMessage());
        }

        //recupero controller dal loader FXML e impostazione del messaggio
        MessageBoxGUIController errorBox = fxmlLoader.getController();
        errorBox.setMessage(message);

        //visualizzazione del dialog box a centro schermo
        dialog.setScene(scene);
        dialog.centerOnScreen();
        dialog.show();
    }

    public static void showExcpetionCLI(String message){
        Printer.printError("\n ERROR: " + message + "\nPress ENTER to continue");
        ScannerSupport.waitEnter();
    }
}
