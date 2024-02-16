package com.example.studyswap.graphiccontroller.cli;

import com.example.studyswap.appcontroller.PurchasedController;
import com.example.studyswap.bean.NoteBean;
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

    private static int i;
    public void start() throws NotFoundException, SQLException {
        setI(1);

        this.purchasedNotesViewCLI = new PurchasedNotesViewCLI(this);
        this.purchasedNotesViewCLI.run();
    }

    public void showPurchasedNotes(){
        PurchasedController purchasedController = new PurchasedController();
        List<Note> allNotes = purchasedController.getPurchasedNotes();


        for (Note note : allNotes){
            Printer.printMessage(i + "| Name: " + note.getFileName() + "| Subject: " + note.getSubject() + "Author: " + note.getAuthor());
            Printer.printMessage("--------------------------------------------------------------------");
            increaseIndex();
        }
    }

    public int askForChoice(){
        Printer.printMessage("Select the notes you want to view");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

    public void openFile(int choice) throws IOException{

        noteBean = new NoteBean(purchasedNotes.get(choice).getFileID(), purchasedNotes.get(choice).getFileName(), purchasedNotes.get(choice).getExtension(), purchasedNotes.get(choice).getContent(), purchasedNotes.get(choice).getPrice(), purchasedNotes.get(choice).getSubject(), purchasedNotes.get(choice).getAuthor());

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

    public static int getI() {
        return i;
    }

    public static void setI(int i) {
        PurchasedNotesCLIController.i = i;
    }

    public static void increaseIndex(){
        i++;
    }
}
