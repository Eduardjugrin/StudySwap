package com.example.studyswap.graphiccontroller.cli;

import com.example.studyswap.dao.NoteDAOJDBC;
import com.example.studyswap.appcontroller.PurchaseController;
import com.example.studyswap.bean.NoteBean;
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
    private static int i = 1;

    public void start() throws NotFoundException, SQLException {
        setI(1);
        this.buyNotesViewCLI = new BuyNotesViewCLI(this);
        this.buyNotesViewCLI.run(this);

    }

    public void showAllNotes() throws NotFoundException {
        allNotes = NoteDAOJDBC.getAllNotes();

        for(Note note : allNotes){
            Printer.printMessage(i + "| Name: " + note.getFileName() + "| Subject: " + note.getSubject() + "Author: " + note.getAuthor());
            Printer.printMessage("--------------------------------------------------------------------");
            increaseIndex();
        }
    }

    public int askForChoice(){
        Printer.printMessage("Select the notes you want to buy");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }
    public void buyNote(int choice)  {

        Note note = allNotes.get(choice);

        NoteBean noteBean = new NoteBean(note.getFileID(), note.getFileName(), note.getExtension(), note.getContent(), note.getUploaderEmail(), note.getPrice(), note.getSubject());

        try {
            boolean isAlreadyPurchased = NoteDAOJDBC.isNotePurchased(Session.getCurrentSession().getBuyerBean().getEmail(), noteBean.getFileId());
            boolean purchaseSuccessful = PurchaseController.buyNote(noteBean);
            if (isAlreadyPurchased) {
                ShowExceptionSupport.showExcpetionCLI("You Have already bought these notes");
            } else if (purchaseSuccessful) {
                Printer.printMessage("Notes purchased successfully");
            }
        } catch (Exception e) {
            ShowExceptionSupport.showExcpetionCLI("Something went wrong.\n Try again.");
        }
    }

    public static int getI() {
        return i;
    }

    public static void setI(int i) {
        BuyNotesCLIController.i = i;
    }

    public static void increaseIndex(){
        i++;
    }
}
