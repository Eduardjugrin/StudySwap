package com.example.StudySwap;

import com.example.StudySwap.Connection.ConnectionDB;
import com.example.StudySwap.engineering.observer.Printer;
import com.example.StudySwap.graphiccontroller.cli.LoginCLIController;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

//public class Main {
public class Main extends Application {
    private static Stage stage;

    public static void main(String[] args) throws SQLException, IOException{
        ConnectionDB.getConnection();

        //scegliere tra GUI e CLI
        Scanner reader = new Scanner(System.in);
        int choice;
        Printer.printMessage("Which type of interface do you want to start?\n" +
                "1) GUI\n" +
                "2) CLI\n" +
                "Insert the number: ");

        while (true) {
            choice = reader.nextInt();
            if(choice == 1){
                //avvio della GUI
                launch();
                break;
            }else if(choice == 2){
                //avvio CLI
                LoginCLIController loginCLIController = new LoginCLIController();
                loginCLIController.start();
            }else{
                Printer.printError("Number not valid, please insert 1 or 2");
            }
        }
    }

    @Override
    public void start (Stage stage)throws Exception{
        //Caricamento layou GUI
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/fxml/loginPage.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        setStage(stage);

        //caricamento proprietà
        stage.setTitle("StudySwap");
        stage.setResizable(false);
        stage.centerOnScreen();
        stage.setScene(scene);
        stage.show();
    }

    public static void setStage(Stage stage){
        Main.stage = stage;
    }

    public static Stage getStage(){
        return stage;
    }

    @Override public void stop() throws SQLException{
        ConnectionDB.closeConnection();
    }
}
