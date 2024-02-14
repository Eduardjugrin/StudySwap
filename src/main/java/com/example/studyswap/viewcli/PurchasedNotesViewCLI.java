package com.example.studyswap.viewcli;

import com.example.studyswap.engineering.observer.Printer;
import com.example.studyswap.engineering.observer.ShowExceptionSupport;
import com.example.studyswap.exception.NotFoundException;
import com.example.studyswap.graphiccontroller.cli.PurchasedNotesCLIController;

import java.io.IOException;
import java.sql.SQLException;

public class PurchasedNotesViewCLI {

    PurchasedNotesCLIController purchasedNotesCLIController;

    public PurchasedNotesViewCLI(PurchasedNotesCLIController purchasedNotesCLIController) {
        this.purchasedNotesCLIController = purchasedNotesCLIController;
    }

    public void run() throws SQLException, NotFoundException {
        Printer.printMessage("-------YOUR PURCHASED NOTES--------");

        purchasedNotesCLIController.showPurchasedNotes();

        int choice = purchasedNotesCLIController.askForChoice();

        if (choice-- > PurchasedNotesCLIController.i) {
            ShowExceptionSupport.showExcpetionCLI("Invalid number");
            purchasedNotesCLIController.start();
        } else {
            try {
                purchasedNotesCLIController.openFile(choice);
            } catch (IOException e) {
                ShowExceptionSupport.showException(e.getMessage());
            }
        }
    }
}
