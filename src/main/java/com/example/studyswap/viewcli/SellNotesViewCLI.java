package com.example.studyswap.viewcli;

import com.example.studyswap.engineering.Printer;
import com.example.studyswap.graphiccontroller.cli.SellNotesCLIController;

import java.io.File;

public class SellNotesViewCLI {

    private File selectedFile;

    SellNotesCLIController sellNotesCLIController;

    public SellNotesViewCLI(SellNotesCLIController sellNotesCLIController){
        this.sellNotesCLIController = sellNotesCLIController;
    }

    public void run(){
        Printer.printMessage("-------SEll NOTES--------");

        File file = sellNotesCLIController.browseFile();
        Printer.printMessage(file.getName());

        Printer.printMessage("Set the subject: ");
        String subject = sellNotesCLIController.setSubject();

        Printer.printMessage("Set the Price: ");
        double price = sellNotesCLIController.setPrice();

        sellNotesCLIController.uploadFile(file, subject, price);

    }

}
