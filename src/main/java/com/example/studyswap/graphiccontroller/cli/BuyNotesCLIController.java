package com.example.studyswap.graphiccontroller.cli;

import com.example.studyswap.dao.NoteDAOJDBC;
import com.example.studyswap.appcontroller.PurchaseController;
import com.example.studyswap.bean.NoteBean;
import com.example.studyswap.engineering.ScannerSupport;
import com.example.studyswap.engineering.singleton.Session;
import com.example.studyswap.engineering.observer.Printer;
import com.example.studyswap.engineering.observer.ShowExceptionSupport;
import com.example.studyswap.exception.NotFoundException;
import com.example.studyswap.model.Note;
import com.example.studyswap.viewcli.BuyNotesViewCLI;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class BuyNotesCLIController {
    private List<Note> allNotes;
    private BuyNotesViewCLI buyNotesViewCLI;
    public int i;

    public void start() throws NotFoundException, SQLException {
        i = 1;
        this.buyNotesViewCLI = new BuyNotesViewCLI(this);
        this.buyNotesViewCLI.run();

    }

    public void showAllNotes() throws NotFoundException {
        allNotes = NoteDAOJDBC.getAllNotes();

        for(Note note : allNotes){
            Printer.printMessage(i + "| Name: " + note.getFileName() + "| Subject: " + note.getSubject() + "Author: " + note.getAuthor());
            Printer.printMessage("--------------------------------------------------------------------");
            i++;
        }
    }

    public int askForChoice(){
        Printer.printMessage("Select the notes you want to buy");
        Scanner scanner = new Scanner(System.in);
        int inputLine = scanner.nextInt();
        return inputLine;
    }
    public void buyNote(int choice) throws SQLException {

        Note note = allNotes.get(choice);

        NoteBean noteBean = new NoteBean(note.getFileID(), note.getFileName(), note.getExtension(), note.getContent(), note.getUploaderEmail(), note.getPrice(), note.getSubject());

        if(NoteDAOJDBC.isNotePurchased(Session.getCurrentSession().getBuyerBean().getEmail(), noteBean.getFileId())){
            ShowExceptionSupport.showExcpetionCLI("You Have already bought these notes");
            return;
        } else if (PurchaseController.buyNote(noteBean)) {
            Printer.printMessage("Notes purchased successfully!");
            Printer.printMessage("PRESS ENTER TO CONTINUE");
            ScannerSupport.waitEnter();
        }
    }
}
