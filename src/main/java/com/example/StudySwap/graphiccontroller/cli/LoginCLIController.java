package com.example.StudySwap.graphiccontroller.cli;

import com.example.StudySwap.appcontroller.LoginController;
import com.example.StudySwap.bean.LoginBean;
import com.example.StudySwap.engineering.observer.ShowExceptionSupport;
import com.example.StudySwap.exception.*;
import com.example.StudySwap.viewcli.LoginViewCLI;

public class LoginCLIController implements GraphicCLIController{

    private static final String LOGIN = "1";
    private static final String LOGIN_WITH_FACEBOOK = "2";
    private static final String LOGIN_WITH_GOOGLE = "3";
    private static final String SIGNUP = "4";

    private LoginViewCLI loginViewCLI;

    @Override
    public void start(){
        //avvio della view del login
        this.loginViewCLI = new LoginViewCLI(this);
        this.loginViewCLI.run();
    }

    public void executeCommand(String inputLine) throws CommandErrorException, NotImplementedException{
        switch(inputLine){

            //avvio della procedura del login
            case LOGIN -> this.loginViewCLI.getCredentials();

            case LOGIN_WITH_GOOGLE, LOGIN_WITH_FACEBOOK, SIGNUP -> throw new NotImplementedException();

            default -> throw new CommandErrorException();
        }
    }

    public void checkLogin(String email, String password){
        try{
            LoginBean loginBean = new LoginBean(email, password);
            LoginController loginController = new LoginController();

            loginController.checkUser(loginBean);

            //verifica dell'utente
            if(loginBean.getRole() == 1){
                loginController.buyerLogin(loginBean);

                BuyerCLIController buyerCLIController = new BuyerCLIController();
                buyerCLIController.start();
            }else if(loginBean.getRole() == 2){
                loginController.sellerLogin(loginBean);

                BuyerCLIController sellerCLIController = new BuyerCLIController();
                sellerCLIController.start();
            }else{
                throw new UserNotFoundException();
            }

        }catch(EmailFormatException | NotFoundException | UserNotFoundException e){
            ShowExceptionSupport.showExcpetionCLI(e.getMessage());
            start();
        }
    }
}
