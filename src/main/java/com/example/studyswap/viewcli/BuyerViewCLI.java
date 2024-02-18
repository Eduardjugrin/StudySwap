package com.example.studyswap.viewcli;

import com.example.studyswap.engineering.Printer;
import com.example.studyswap.engineering.ShowExceptionSupport;
import com.example.studyswap.exception.CommandErrorException;
import com.example.studyswap.exception.NotFoundException;
import com.example.studyswap.graphiccontroller.cli.BuyerCLIController;

import java.sql.SQLException;
import java.util.Scanner;

public class BuyerViewCLI {
    private final BuyerCLIController buyerCLIController;

    public BuyerViewCLI(BuyerCLIController buyerCLIController) {
        this.buyerCLIController = buyerCLIController;
    }

    public void run() {
        Printer.printMessage("\n--------BUYER HOMEPAGE--------");
        Printer.printMessage("""
                1) View available notes
                2) View purchased notes
                3) Logout
                """);


        Scanner scanner = new Scanner(System.in);
        String inputLine = scanner.nextLine();

        try {
            this.buyerCLIController.executeCommand(inputLine);
        } catch (CommandErrorException | NotFoundException | SQLException e) {
            ShowExceptionSupport.showExcpetionCLI(e.getMessage());
            run();
        }
    }
}
