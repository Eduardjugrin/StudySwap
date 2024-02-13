package com.example.StudySwap.graphiccontroller.cli;

import com.example.StudySwap.DAO.NoteDAOJDBC;
import com.example.StudySwap.engineering.observer.Printer;
import com.example.StudySwap.exception.NotFoundException;
import com.example.StudySwap.model.Note;
import com.example.StudySwap.viewcli.BuyNotesViewCLI;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class BuyNotesCLIController {
    private List<Note> allNotes;
    private BuyNotesViewCLI buyNotesViewCLI;

    public void start() throws NotFoundException, SQLException {
        this.buyNotesViewCLI = new BuyNotesViewCLI();
        this.buyNotesViewCLI.run();

        buyNotesViewCLI.ShowAllNotes();

        Printer.printMessage("Select the row you'd like to buy:");
        Scanner scanner = new Scanner(System.in);
        int inputLine = scanner.nextInt();

        buyNotesViewCLI.buyNote(inputLine);
    }

}
