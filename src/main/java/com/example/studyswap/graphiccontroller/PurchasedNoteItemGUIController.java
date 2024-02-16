package com.example.studyswap.graphiccontroller;

import com.example.studyswap.bean.NoteBean;
import com.example.studyswap.engineering.observer.Printer;
import com.example.studyswap.engineering.observer.ShowExceptionSupport;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.awt.*;
import java.io.*;
import java.nio.file.Files;
import java.security.Security;

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

                    //todo

                }
            }
        catch (IOException | SecurityException e) {
            Printer.printError(e.getMessage());
        } finally {
            if (!tempFile.delete()) {
                Printer.printMessage("An error ocured while deleting tempDir");
            }
        }
    }

    public void setNoteBean(NoteBean noteBean) {
        this.noteBean = noteBean;
    }

    public void leaveReview() {
        ShowExceptionSupport.showException(NOT_IMPLEMENTED);
    }
}

