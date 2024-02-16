package com.example.studyswap.viewcli;

import com.example.studyswap.engineering.observer.Printer;
import com.example.studyswap.exception.CommandErrorException;
import com.example.studyswap.exception.NotImplementedException;
import com.example.studyswap.graphiccontroller.cli.UploadedCLIController;

import java.util.Scanner;

public class UploadedViewCLI {

    UploadedCLIController uploadedCLIController;


    public UploadedViewCLI(UploadedCLIController uploadedCLIController){
        this.uploadedCLIController = uploadedCLIController;
    }

    public void run(){
        Printer.printMessage("-------YOUR UPLOADED NOTES--------");

        uploadedCLIController.showUploadedNotes();

        Printer.printMessage("select a note");
        int choice = uploadedCLIController.getChoice();

        Printer.printMessage("1) Edit details\n" +
                "2)View reviews");

        Scanner scanner = new Scanner(System.in);
        String inputLine = scanner.nextLine();

        try{
            this.uploadedCLIController.executeCommand(inputLine);
        }catch(CommandErrorException | NotImplementedException e){
            Printer.printMessage(e.getMessage());
        }
    }
}
