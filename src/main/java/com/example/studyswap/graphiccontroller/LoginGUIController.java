package com.example.studyswap.graphiccontroller;

import com.example.studyswap.Main;
import com.example.studyswap.appcontroller.LoginController;
import com.example.studyswap.bean.LoginBean;
import com.example.studyswap.engineering.Printer;
import com.example.studyswap.engineering.ShowExceptionSupport;
import com.example.studyswap.exception.EmailFormatException;
import com.example.studyswap.exception.NotFoundException;
import com.example.studyswap.exception.UserNotFoundException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class LoginGUIController {

    @FXML
    TextField emailTextField;

    @FXML
    PasswordField passwordTextField;

    private static final String NOT_IMPLEMENTED = "Not implemented yet";

    public void checkLogin() throws IOException, NotFoundException{

        try{
            //creazione LoginBean con i dati inseriti dall'utente
            LoginBean loginBean = new LoginBean(emailTextField.getText(),passwordTextField.getText());
            LoginController loginController = new LoginController();

            loginController.checkUser(loginBean);

            Parent root;
            Stage dialog = Main.getStage();

            switch(loginBean.getRole()){
                case 1 ->{ //1 = buyer
                    loginController.buyerLogin(loginBean);
                    root = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("/fxml/buyerHomepage.fxml")));
                }

                case 2 ->{
                    loginController.sellerLogin(loginBean);
                    root = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("/fxml/sellerHomepage.fxml")));
                }

                default -> throw new UserNotFoundException();
            }

            Scene scene = new Scene(root);
            dialog.setScene(scene);
            dialog.show();
        }catch (EmailFormatException | UserNotFoundException e){
            ShowExceptionSupport.showException(e.getMessage());
        }catch(IOException e){
            Printer.printError(e.getMessage());
        }
    }

    //per effettuare login teramite facebook, google e creare un account
    public void facebookLogin(){
        ShowExceptionSupport.showException(NOT_IMPLEMENTED);
    }

    public void googleLogin(){
        ShowExceptionSupport.showException(NOT_IMPLEMENTED);
    }

    public void createNewAccount(){
        ShowExceptionSupport.showException(NOT_IMPLEMENTED);
    }
}
