package com.example.StudySwap.viewcli;

import com.example.StudySwap.engineering.observer.Printer;
import com.example.StudySwap.engineering.observer.ShowExceptionSupport;
import com.example.StudySwap.exception.NotFoundException;
import com.example.StudySwap.graphiccontroller.cli.BuyNotesCLIController;

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
