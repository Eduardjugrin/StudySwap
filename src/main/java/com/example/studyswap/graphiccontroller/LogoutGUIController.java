package com.example.studyswap.graphiccontroller;

import com.example.studyswap.Main;
import com.example.studyswap.appcontroller.LogoutController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class LogoutGUIController {

    //metodo per effettuare il logout
    public void logout() throws IOException{
        LogoutController logoutController = new LogoutController();
        logoutController.logout();

        Stage stage = Main.getStage();
        Parent root = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("/fxml/loginPage.fxml")));
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }
}
