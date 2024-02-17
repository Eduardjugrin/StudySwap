package com.example.studyswap.viewcli;

import com.example.studyswap.engineering.observer.Printer;
import com.example.studyswap.engineering.observer.ShowExceptionSupport;
import com.example.studyswap.exception.NotFoundException;
import com.example.studyswap.graphiccontroller.cli.PurchasedNotesCLIController;

import java.io.IOException;
import java.sql.SQLException;

public class PurchasedNotesViewCLI {

    PurchasedNotesCLIController purchasedNotesCLIController;
    private static final int OPEN_FILE = 1;
    private static final int LEAVE_REVIEW = 2;

    public PurchasedNotesViewCLI(PurchasedNotesCLIController purchasedNotesCLIController) {
        this.purchasedNotesCLIController = purchasedNotesCLIController;
    }

    public void run() throws SQLException, NotFoundException {
        Printer.printMessage("-------YOUR PURCHASED NOTES--------");

        purchasedNotesCLIController.showPurchasedNotes();

        Printer.printMessage("Select a file: ");
        int choice = purchasedNotesCLIController.askForChoice();

        if (choice-- >= PurchasedNotesCLIController.getI()) {
            ShowExceptionSupport.showExcpetionCLI("Invalid number");
            purchasedNotesCLIController.start();
        } else {
            Printer.printMessage("""
                1) OpenFile
                2) Leave a review""");
            int inputLine = purchasedNotesCLIController.askForChoice();
            switch(inputLine){
                case OPEN_FILE -> {
                    try {
                        purchasedNotesCLIController.openFile(choice);
                    } catch (IOException e) {
                        ShowExceptionSupport.showException(e.getMessage());
                    }
                }
                case LEAVE_REVIEW -> {
                    purchasedNotesCLIController.leaveReview(choice);
                }
            }
        }
    }
}
