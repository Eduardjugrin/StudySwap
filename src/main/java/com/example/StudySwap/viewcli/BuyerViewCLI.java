package com.example.StudySwap.viewcli;

import com.example.StudySwap.engineering.observer.Printer;
import com.example.StudySwap.engineering.observer.ShowExceptionSupport;
import com.example.StudySwap.exception.CommandErrorException;
import com.example.StudySwap.exception.NotFoundException;
import com.example.StudySwap.exception.NotImplementedException;
import com.example.StudySwap.graphiccontroller.cli.BuyerCLIController;

import java.sql.SQLException;
import java.util.Scanner;

public class BuyerViewCLI {
    private final BuyerCLIController buyerCLIController;

    public BuyerViewCLI(BuyerCLIController buyerCLIController){
        this.buyerCLIController = buyerCLIController;
    }

    public void run(){
        Printer.printMessage("\n--------BUYER HOMEPAGE--------");
        Printer.printMessage("1) View available notes\n" +
                "2) View purchased notes\n" +
                "3) Logout");

        Scanner scanner = new Scanner(System.in);
        String inputLine = scanner.nextLine();

        try{
            this.buyerCLIController.executeCommand(inputLine);
        }catch(CommandErrorException | NotImplementedException | NotFoundException | SQLException e){
            ShowExceptionSupport.showExcpetionCLI(e.getMessage());
            run();
        }
    }
}
