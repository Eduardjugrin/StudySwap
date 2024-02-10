package com.example.StudySwap.graphiccontroller;

import com.example.StudySwap.DAO.NoteDAOJDBC;
import com.example.StudySwap.bean.NoteBean;
import com.example.StudySwap.engineering.observer.Printer;
import com.example.StudySwap.engineering.observer.ShowExceptionSupport;
import com.example.StudySwap.exception.NotFoundException;
import com.example.StudySwap.model.Note;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
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


    private String NOT_IMPLEMENTED = "Not implemented yet";

    public void setData(NoteBean setNote){
        noteTitleLabel.setText(String.valueOf(setNote.getFileName()));
        subjectLabel.setText(String.valueOf(setNote.getSubject()));
        priceLabel.setText("€" + String.format("%.2f", setNote.getPrice()));
        authorLabel.setText(String.valueOf(setNote.getAuthor()));
    }

    public void viewNotes() throws IOException{

        new File("/secureDir/myFile.pdf");
        File.createTempFile(noteBean.getFileName(), ".pdf");


        try{
            byte[] pdfData = noteBean.getContent();

            if(pdfData != null){
                try(InputStream inputStream = new ByteArrayInputStream(pdfData)){
                    File tempFile = File.createTempFile(noteBean.getFileName(), ".pdf");

                    try(FileOutputStream fos = new FileOutputStream(tempFile)){
                        IOUtils.copy(inputStream, fos);
                    }
                    Desktop.getDesktop().open(tempFile);
                }
            }

        }catch(IOException e){
            Printer.printError(e.getMessage());
        }
    }

    public void setNoteBean(NoteBean noteBean){
        this.noteBean = noteBean;
    }
}
