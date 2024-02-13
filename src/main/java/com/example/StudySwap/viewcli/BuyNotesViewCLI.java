package com.example.StudySwap.viewcli;

import com.example.StudySwap.DAO.NoteDAOJDBC;
import com.example.StudySwap.appcontroller.PurchaseController;
import com.example.StudySwap.bean.NoteBean;
import com.example.StudySwap.engineering.Singleton.Session;
import com.example.StudySwap.engineering.observer.Printer;
import com.example.StudySwap.engineering.observer.ShowExceptionSupport;
import com.example.StudySwap.exception.NotFoundException;
import com.example.StudySwap.model.Note;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class BuyNotesViewCLI {

    private List<Note> allNotes;
    public int i = 0;

    public void run(){
        Printer.printMessage("-------BUY NOTES--------");
    }

    public void ShowAllNotes() throws NotFoundException {
        allNotes = NoteDAOJDBC.getAllNotes();

        for(Note note : allNotes){
            Printer.printMessage(i + "| Name: " + note.getFileName() + "| Subject: " + note.getSubject() + "Author: " + note.getAuthor());
            Printer.printMessage("--------------------------------------------------------------------");
            i++;
        }
    }

    public void buyNote(int choice) throws SQLException {
        System.out.println(choice);

        Note note = allNotes.get(choice);
        NoteBean noteBean = new NoteBean(note.getFileID(), note.getFileName(), note.getExtension(), note.getContent(), note.getUploaderEmail(), note.getPrice(), note.getSubject());

        if(NoteDAOJDBC.isNotePurchased(Session.getCurrentSession().getBuyerBean().getEmail(), noteBean.getFileId())){
            ShowExceptionSupport.showExcpetionCLI("You Have already bought these notes");
            return;
        } else if (PurchaseController.buyNote(noteBean)) {
            Printer.printMessage("Notes purchased successfully");
        }
    }

}
