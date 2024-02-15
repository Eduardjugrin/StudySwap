package com.example.studyswap.graphiccontroller.cli;

import com.example.studyswap.DAO.NoteDAOJDBC;
import com.example.studyswap.bean.NoteBean;
import com.example.studyswap.engineering.Singleton.Session;
import com.example.studyswap.engineering.observer.Printer;
import com.example.studyswap.exception.NotFoundException;
import com.example.studyswap.model.Note;
import com.example.studyswap.viewcli.PurchasedNotesViewCLI;
import org.apache.commons.io.IOUtils;

import java.awt.*;
import java.io.*;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class PurchasedNotesCLIController {
    private List<Note> purchasedNotes;
    private PurchasedNotesViewCLI purchasedNotesViewCLI;

    private NoteBean noteBean = null;

    public static int i;
    public void start() throws NotFoundException, SQLException {
        i = 1;

        this.purchasedNotesViewCLI = new PurchasedNotesViewCLI(this);
        this.purchasedNotesViewCLI.run();
    }

    public void showPurchasedNotes(){
        purchasedNotes = NoteDAOJDBC.getPurchasedNotes(Session.getCurrentSession().getBuyerBean().getEmail());

        for (Note note : purchasedNotes){
            Printer.printMessage(i + "| Name: " + note.getFileName() + "| Subject: " + note.getSubject() + "Author: " + note.getAuthor());
            Printer.printMessage("--------------------------------------------------------------------");
            i++;
        }
    }

    public int askForChoice(){
        Printer.printMessage("Select the notes you want to view");
        Scanner scanner = new Scanner(System.in);
        int inputLine = scanner.nextInt();
        return inputLine;
    }

    public void openFile(int choice) throws IOException{

        noteBean = new NoteBean(purchasedNotes.get(choice).getFileID(), purchasedNotes.get(choice).getFileName(), purchasedNotes.get(choice).getExtension(), purchasedNotes.get(choice).getContent(), purchasedNotes.get(choice).getUploaderEmail(), purchasedNotes.get(choice).getPrice(), purchasedNotes.get(choice).getSubject(), purchasedNotes.get(choice).getAuthor());

        String tempDirPath = System.getProperty("java.io.tmpdir");
        File tempDir = new File(tempDirPath + File.separator + "my_apptemp");
        tempDir.mkdirs();
        File.createTempFile(noteBean.getFileName(), ".pdf");


        try{
            File tempFile = File.createTempFile(noteBean.getFileName(), ".pdf", tempDir);
            tempFile.deleteOnExit();
            byte[] pdfData = noteBean.getContent();


            if(pdfData != null){
                try(InputStream inputStream = new ByteArrayInputStream(pdfData)){

                    try(FileOutputStream fos = new FileOutputStream(tempFile)){
                        IOUtils.copy(inputStream, fos);
                        Desktop.getDesktop().open(tempFile);

                        //todo
                    }
                }
            }

        }catch(IOException | SecurityException e){
            Printer.printError(e.getMessage());
        }finally {
            if(!tempDir.delete()){
                Printer.printError("an error occured deleting tempDir");
            }
        }
    }


}
