package com.example.StudySwap.graphiccontroller;

import com.example.StudySwap.Main;
import com.example.StudySwap.appcontroller.NoteUploadController;
import com.example.StudySwap.bean.NoteBean;
import com.example.StudySwap.bean.SellerBean;
import com.example.StudySwap.engineering.Session;
import com.example.StudySwap.engineering.ShowExceptionSupport;
import com.example.StudySwap.exception.DuplicateNoteException;
import com.example.StudySwap.exception.NotFoundException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.DecimalFormat;
import java.text.ParsePosition;
import java.util.Objects;

public class SellNotesGUIController {

    @FXML
    private TextField filePathField;

    @FXML
    private Button browseButton;

    @FXML Button uploadButton;

    @FXML
    private TextField subject;

    @FXML
    private Spinner<Double> priceSpinner;


    private File selectedFile;

    private static final String SELLER_HP = "SellerHomepage.fxml";

    public void initialize(){
        DecimalFormat format = new DecimalFormat("#.##"); // Formato per i numeri decimali con due cifre decimali
        TextFormatter<Double> textFormatter = new TextFormatter<>(c -> {
            if (c.getControlNewText().isEmpty()) {
                return c;
            }
            ParsePosition parsePosition = new ParsePosition(0);
            Object object = format.parse(c.getControlNewText(), parsePosition);
            if (object == null || parsePosition.getIndex() < c.getControlNewText().length()) {
                // Il testo non è completamente numerico
                return null;
            } else {
                // Il testo è completamente numerico
                return c;
            }
        });
        priceSpinner.getEditor().setTextFormatter(textFormatter);
        priceSpinner.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(Float.MIN_VALUE, Float.MAX_VALUE, 0.0, 0.01));
    }



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

            if(selectedFile == null){
                ShowExceptionSupport.showException("invalid path");
            }else if(subject == null){
                ShowExceptionSupport.showException("subject field is empty");
            }else if(priceSpinner == null){
                ShowExceptionSupport.showException("insert a price");
            }else{
                    //leggere il contenuto del file come array di byte
                    byte[] fileContent = Files.readAllBytes(selectedFile.toPath());

                    //creazione dell'oggetto bean con i dati del file
                    NoteBean noteBean = new NoteBean(selectedFile.getName(), getFileExtension(selectedFile.getName()), fileContent, sellerEmail, priceSpinner.getValue(), subject.getText());

                    //caricamento del file tramite il DAO
                    NoteUploadController noteUploadController = new NoteUploadController();
                    noteUploadController.uploadFile(noteBean, sellerEmail);

                    ShowExceptionSupport.showException("file caricato con successo");
                }

        }catch(DuplicateNoteException e){
            ShowExceptionSupport.showException(e.getMessage());
        }
        catch(IOException e){
            ShowExceptionSupport.showException(e.getMessage());
        }

    }

    private String getFileExtension(String fileName){
        int dotIndex = fileName.lastIndexOf(".");
        return dotIndex == -1 ? "" : fileName.substring(dotIndex + 1);
    }

    public void toPreviousScreen() throws IOException{
        Stage stage = Main.getStage();
        Parent root = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource(SELLER_HP)));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void logout() throws IOException{
        System.out.println("aaa");
    }
}
