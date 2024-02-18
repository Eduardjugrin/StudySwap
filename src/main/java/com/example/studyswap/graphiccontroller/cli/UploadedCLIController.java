package com.example.studyswap.graphiccontroller.cli;

import com.example.studyswap.dao.NoteDAOJDBC;
import com.example.studyswap.engineering.singleton.Session;
import com.example.studyswap.engineering.Printer;
import com.example.studyswap.exception.CommandErrorException;
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

    private static int i;

    public void start() {
        setI(1);

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
                    increaseIndex();
                }
            }

        } catch (Exception e) {
            Printer.printError(e.getMessage());
        }
    }

    public int getChoice() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

    public void executeCommand( String inputLine) throws CommandErrorException, NotImplementedException {
        if (inputLine.equals(EDIT_DETAILS) || inputLine.equals(VIEW_REVIEWS)) {
            throw new NotImplementedException();
        } else {
            throw new CommandErrorException();
        }

    }

    public static int getI() {
        return i;
    }

    public static void setI(int i) {
        UploadedCLIController.i = i;
    }

    public static void increaseIndex() {
        i++;
    }

}
