package com.example.studyswap.graphiccontroller;

import com.example.studyswap.Main;
import com.example.studyswap.appcontroller.PurchasedController;
import com.example.studyswap.bean.NoteBean;
import com.example.studyswap.engineering.observer.Printer;
import com.example.studyswap.engineering.observer.ShowExceptionSupport;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;

import java.awt.*;
import java.io.*;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.channels.OverlappingFileLockException;
import java.nio.file.*;
import java.security.Security;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import javafx.stage.Stage;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

public class PurchasedNoteItemGUIController {

    @FXML
    private Label noteTitleLabel;
    @FXML
    private Label subjectLabel;
    @FXML
    private Label priceLabel;
    @FXML
    private Label authorLabel;
    private NoteBean noteBean;


    private static final String NOT_IMPLEMENTED = "Not implemented yet";
    private static final String LEAVE_REVIEW = "/fxml/leaveReviewPage.fxml";

    public void setData(NoteBean setNote) {
        noteTitleLabel.setText(String.valueOf(setNote.getFileName()));
        subjectLabel.setText(String.valueOf(setNote.getSubject()));
        priceLabel.setText("€" + String.format("%.2f", setNote.getPrice()));
        authorLabel.setText(String.valueOf(setNote.getAuthor()));
    }

    public void viewNotes(){

        File tempFile = null;
        try {
            byte[] pdfData = noteBean.getContent();
            if (pdfData != null) {
                tempFile = File.createTempFile("tempFile", ".pdf");
                Files.write(tempFile.toPath(), pdfData);
                Desktop.getDesktop().open(tempFile);
            }
        }
        catch (IOException | SecurityException e) {
            Printer.printError(e.getMessage());
        }
    }

    public void setNoteBean(NoteBean noteBean) {
        this.noteBean = noteBean;
    }

    public void leaveReview() throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("LEAVE_REVIEW"));
        Parent root = loader.load();
        LeaveReviewGUIController leaveReviewGUIController = loader.getController();
        leaveReviewGUIController.setNoteBean(noteBean); // Passa il bean NoteBean a LeaveReviewGUIController

        Stage stage = Main.getStage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public  NoteBean getNoteBean(){
        return noteBean;
    }


}

