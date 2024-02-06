package com.example.StudySwap.graphiccontroller;

import com.example.StudySwap.DAO.NoteDAO;
import com.example.StudySwap.DAO.NoteDAOJDBC;
import com.example.StudySwap.appcontroller.NoteUploadController;
import com.example.StudySwap.bean.NoteBean;
import com.example.StudySwap.bean.SellerBean;
import com.example.StudySwap.engineering.Printer;
import com.example.StudySwap.engineering.Session;
import com.example.StudySwap.exception.DuplicateNoteException;
import com.example.StudySwap.exception.NotFoundException;
import com.example.StudySwap.model.Note;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class sellerGUIController {

    @FXML
    private TextField filePathField;

    @FXML
    private Button browseButton;

    @FXML Button uploadButton;

    private File selectedFile;


    @FXML
    public void  browseFile(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Seleziona il file da caricare");

        //impostazione filtri per specificare il tipo del file
        FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("File PDF (*.pdf)", "*.pdf");
        fileChooser.getExtensionFilters().add(extensionFilter);

        //mostra la finestra di dialogo per la selezione del file
        selectedFile = fileChooser.showOpenDialog( new Stage());

        if(selectedFile != null){
            filePathField.setText((selectedFile.getAbsolutePath()));
        }

    }

    @FXML
    public void uploadFile() throws NotFoundException{
        SellerBean sellerBean = Session.getCurrentSession().getSellerBean();
        String sellerEmail = sellerBean.getEmail();

        try{
            //leggere il contenuto del file come array di byte
            byte[] fileContent = Files.readAllBytes(selectedFile.toPath());

            //creazione dell'oggetto bean con i dati del file
            NoteBean noteBean = new NoteBean(selectedFile.getName(), getFileExtension(selectedFile.getName()), fileContent, sellerEmail);

            //caricamento del file tramite il DAO
            NoteUploadController noteUploadController = new NoteUploadController();
            noteUploadController.uploadFile(noteBean, sellerEmail);


        }catch(IOException | DuplicateNoteException e){
            Printer.printError(e.getMessage());
        }
    }

    private String getFileExtension(String fileName){
        int dotIndex = fileName.lastIndexOf(".");
        return dotIndex == -1 ? "" : fileName.substring(dotIndex + 1);
    }



}
