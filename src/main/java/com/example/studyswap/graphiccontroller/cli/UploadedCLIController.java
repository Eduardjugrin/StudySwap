package com.example.studyswap.graphiccontroller.cli;

import com.example.studyswap.DAO.NoteDAOJDBC;
import com.example.studyswap.appcontroller.LogoutController;
import com.example.studyswap.engineering.Singleton.Session;
import com.example.studyswap.engineering.observer.Printer;
import com.example.studyswap.exception.CommandErrorException;
import com.example.studyswap.exception.NotFoundException;
import com.example.studyswap.exception.NotImplementedException;
import com.example.studyswap.model.Note;
import com.example.studyswap.viewcli.UploadedViewCLI;

import java.util.List;
import java.util.Scanner;

public class UploadedCLIController {

    private static final String EDIT_DETAILS = "1";
    private static final String VIEW_REVIEWS = "2";

    private List<Note> uploadedNotes;

    UploadedViewCLI uploadedViewCLI;

    public static int i;

    public void start() {
        i = 1;

        this.uploadedViewCLI = new UploadedViewCLI(this);
        this.uploadedViewCLI.run();
    }

    public void showUploadedNotes() {
        try {
            uploadedNotes = NoteDAOJDBC.getAllNotesBySeller(Session.getCurrentSession().getSellerBean().getEmail());
            if (uploadedNotes.isEmpty()) {
                Printer.printMessage("No notes uploaded yet");
            } else {
                for (Note note : uploadedNotes) {
                    Printer.printMessage(i + "| Name: " + note.getFileName() + "| Subject: " + note.getSubject());
                    Printer.printMessage("--------------------------------------------------------------------");
                    i++;
                }
            }

        } catch (NotFoundException e) {
            Printer.printError(e.getMessage());
        }
    }

    public int getChoice(){
        Scanner scanner = new Scanner(System.in);
        int inputLine = scanner.nextInt();
        return inputLine;
    }

    public void executeCommand(String inputLine) throws CommandErrorException, NotImplementedException {
        switch (inputLine){
            case EDIT_DETAILS, VIEW_REVIEWS -> throw new NotImplementedException();

            default -> throw new CommandErrorException();
        }
    }
}
