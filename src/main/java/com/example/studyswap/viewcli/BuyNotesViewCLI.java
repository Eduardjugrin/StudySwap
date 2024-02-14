package com.example.studyswap.viewcli;

import com.example.studyswap.engineering.observer.Printer;
import com.example.studyswap.engineering.observer.ShowExceptionSupport;
import com.example.studyswap.exception.NotFoundException;
import com.example.studyswap.graphiccontroller.cli.BuyNotesCLIController;

import java.sql.SQLException;

public class BuyNotesViewCLI {

    BuyNotesCLIController buyNotesCLIController;

    public BuyNotesViewCLI(BuyNotesCLIController buyNotesCLIController){
        this.buyNotesCLIController = buyNotesCLIController;
    }

    public void run() throws SQLException, NotFoundException{
        Printer.printMessage("-------BUY NOTES--------");

        try {
            buyNotesCLIController.showAllNotes();
        }catch(NotFoundException e){
            ShowExceptionSupport.showExcpetionCLI(e.getMessage());
            buyNotesCLIController.start();

        }

        int choice = buyNotesCLIController.askForChoice();

        if(choice-- > buyNotesCLIController.i){
            ShowExceptionSupport.showExcpetionCLI("Invalid number");
            buyNotesCLIController.start();
        }else{
            try{
                buyNotesCLIController.buyNote(choice);
            }catch(SQLException e){
                ShowExceptionSupport.showExcpetionCLI(e.getMessage());
                buyNotesCLIController.start();
            }
        }
    }
}
