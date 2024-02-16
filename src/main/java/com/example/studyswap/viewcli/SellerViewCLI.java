package com.example.studyswap.viewcli;

import com.example.studyswap.engineering.observer.Printer;
import com.example.studyswap.exception.CommandErrorException;
import com.example.studyswap.exception.NotImplementedException;
import com.example.studyswap.graphiccontroller.cli.SellerCLIController;

import java.util.Scanner;

public class SellerViewCLI {

    private final SellerCLIController sellerCLIController;
    public SellerViewCLI(SellerCLIController sellerCLIController){
        this.sellerCLIController = sellerCLIController;
    }

    public void run(){
        Printer.printMessage("\n--------SELLER HOMEPAGE--------");
        Printer.printMessage("1) Sell your notes\n" +
                "2) View yout notes\n" +
                "3) Logout");

        Scanner scanner = new Scanner(System.in);
        String inputLine = scanner.nextLine();

        try{
            this.sellerCLIController.executeCommand(inputLine);
        }catch(CommandErrorException e){
            Printer.printMessage(e.getMessage());
            run();
        }
    }
}
