package com.example.StudySwap.viewcli;

import com.example.StudySwap.engineering.observer.Printer;
import com.example.StudySwap.engineering.observer.ShowExceptionSupport;
import com.example.StudySwap.exception.NotFoundException;
import com.example.StudySwap.graphiccontroller.cli.PurchasedNotesCLIController;

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
        } else {
            try {
                purchasedNotesCLIController.openFile(choice);
            } catch (IOException e) {
                ShowExceptionSupport.showException(e.getMessage());
            }
        }
    }
}
