package com.example.studyswap.viewcli;

import com.example.studyswap.engineering.observer.Printer;
import com.example.studyswap.engineering.observer.ShowExceptionSupport;
import com.example.studyswap.exception.CommandErrorException;
import com.example.studyswap.exception.NotImplementedException;
import com.example.studyswap.graphiccontroller.cli.LoginCLIController;

import java.util.Scanner;

public class LoginViewCLI {

    private final LoginCLIController loginCLIController;

    public LoginViewCLI(LoginCLIController cliLoginControllerCurrent){
        this.loginCLIController = cliLoginControllerCurrent;
    }

    public void run(){
        //opzione di login

        Printer.printMessage("\n--------LOGIN PAGE--------");
        Printer.printMessage("1) Login\n" +
                "2) Login with Facebook\n" +
                "3)Login with Google\n" +
                "4) Sign up");

        Scanner scanner = new Scanner(System.in);
        String inputLine = scanner.nextLine();

        try{
            this.loginCLIController.executeCommand(inputLine);
        }catch(CommandErrorException | NotImplementedException e){
            ShowExceptionSupport.showExcpetionCLI(e.getMessage());
            run();
        }
    }

    public void getCredentials(){
        Scanner scanner = new Scanner(System.in);

        Printer.printMessage("\nInsert email: ");
        String email = scanner.nextLine();

        Printer.printMessage("\nInsert password: ");
        String password = scanner.nextLine();

        try{
            //verifica credenziali
            loginCLIController.checkLogin(email, password);
        }catch(Exception e){
            Printer.printError(e.getMessage());
        }
    }
}

