package com.example.studyswap.dao;

import com.example.studyswap.engineering.Printer;
import com.example.studyswap.model.Note;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//gestione dell'accesso ai dati degli appunti
public class NoteDAOCSV extends NoteDAO {
    private static final String CSV_FILE_PATH = "src/main/java/res/purchased.csv";
    private static final int ID = 0;
    private static final int BUYER_EMAIL = 1;
    private static final int FILE_ID = 2;
    private static final int PURCHASE_TIME = 3;

    public List<Note> getPurchasedNotes(String buyerEmail) {
        List<Note> purchasedNotes = new ArrayList<>();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(CSV_FILE_PATH))) {
            String row;
            String[] data;

            while ((row = bufferedReader.readLine()) != null) {

                data = row.split(",");

                if (data[BUYER_EMAIL].equals(buyerEmail)) {
                    purchasedNotes.add(getNoteById(Integer.parseInt(data[FILE_ID])));
                }
            }
        } catch (IOException | SQLException e) {
            Printer.printError(e.getMessage());
        }

        return purchasedNotes;
    }

}
