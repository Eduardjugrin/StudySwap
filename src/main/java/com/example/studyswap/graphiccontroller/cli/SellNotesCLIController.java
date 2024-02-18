package com.example.studyswap.graphiccontroller.cli;

import com.example.studyswap.appcontroller.NoteUploadController;
import com.example.studyswap.bean.NoteBean;
import com.example.studyswap.bean.SellerBean;
import com.example.studyswap.engineering.singleton.Session;
import com.example.studyswap.engineering.Printer;
import com.example.studyswap.engineering.ShowExceptionSupport;
import com.example.studyswap.exception.DuplicateNoteException;
import com.example.studyswap.viewcli.SellNotesViewCLI;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.util.Scanner;

public class SellNotesCLIController {

    private SellNotesViewCLI sellNotesViewCLI;

    public void start(){
        this.sellNotesViewCLI = new SellNotesViewCLI(this);
        this.sellNotesViewCLI.run();
    }

    public File browseFile(){
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        Printer.printMessage("Enter the file path of the file you want to upload: ");
        File file = null;

        String filePath = null;
        try{
            filePath = bufferedReader.readLine();

            file = new File(filePath);

            if(file.exists()){
                Printer.printMessage("Selected file: " +filePath);
            }else{
                Printer.printError("Selected file does not exist");
            }
        }catch(IOException e){
            Printer.printError(e.getMessage());
        }

        return file;
    }

    public double setPrice(){
        Scanner scanner = new Scanner(System.in);
        return scanner.nextDouble();
    }

    public String setSubject(){
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public void uploadFile(File selectedFile, String subject, double price){
        SellerBean sellerBean = Session.getCurrentSession().getSellerBean();
        String sellerEmail = sellerBean.getEmail();


        try{

            if(selectedFile == null || subject.isEmpty() || price == 0.0){
                Printer.printMessage(selectedFile + " " + subject + " " + price);
                ShowExceptionSupport.showExcpetionCLI("You must fill all the fields");
                sellNotesViewCLI.run();
            }else{
                //leggere il contenuto del file come array di byte
                byte[] fileContent = Files.readAllBytes(selectedFile.toPath());

                //creazione dell'oggetto bean con i dati del file
                NoteBean noteBean = new NoteBean(selectedFile.getName(), getFileExtension(selectedFile.getName()), fileContent, sellerEmail, price, subject);

                //caricamento del file tramite il DAO
                NoteUploadController noteUploadController = new NoteUploadController();
                noteUploadController.sellFile(noteBean, sellerEmail);

                ShowExceptionSupport.showExcpetionCLI("File uploaded successfully!");
            }

        }catch(DuplicateNoteException | IOException e){
            ShowExceptionSupport.showExcpetionCLI(e.getMessage());
        }


    }
    private String getFileExtension(String fileName){
        int dotIndex = fileName.lastIndexOf(".");
        return dotIndex == -1 ? "" :fileName.substring(dotIndex + 1);
    }
}
