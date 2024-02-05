//package com.example.StudySwap.DAO;
//
//import com.example.StudySwap.exception.AuthorNotFoundException;
//import com.example.StudySwap.engineering.Printer;
//import com.example.StudySwap.model.Note;
//
//import java.io.BufferedReader;
//import java.io.FileReader;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//
////gestione dell'accesso ai dati degli appunti
//public class NoteDAOCSV implements NoteDAO{
//    //percorso per il file CSV
//    private static final String CSV_FILE_NAME = "src/main/java/res/notes.csv";
//
//    //indici delle colonne del file csv
//    private static final int TITLE = 0;
//    private static final int PATH = 1;
//    private static final int AUTHOR = 2;
//    private static final int PRICE = 3;
//
//
//    @Override
//    public List<Note> getAllNotes(){
//        List<Note> notes = new ArrayList<>();
//
//        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(CSV_FILE_NAME))){
//            String row;
//            String [] data;
//
//            while((row= bufferedReader.readLine()) != null){
//                data = row.split(",");
//
//                Note note = new Note(data[TITLE],data[PATH], data[AUTHOR], data[PRICE]);
//                notes.add(note);
//            }
//        }catch(IOException e){
//            Printer.printError(e.getMessage());
//        }
//        return notes;
//    }
//
//
//    @Override
//    public List<Note> getNotesByAuthor(String author) throws AuthorNotFoundException {
//        List<Note> notesByAuthor = new ArrayList<>();
//        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(CSV_FILE_NAME))){
//            String row;
//            String[] data;
//
//            while((row = bufferedReader.readLine()) != null){
//                data = row.split(",");
//                if(data[AUTHOR].equals(author)){
//                    Note note = new Note(data[TITLE]
////                            ,data[PATH], data[AUTHOR], data[PRICE]
//                    );
//                    notesByAuthor.add(note);
//                }
//            }
//
//            if(notesByAuthor.isEmpty()){
//                throw new AuthorNotFoundException();
//            }
//
//        }catch(IOException e){
//            Printer.printError(e.getMessage());
//        }
//        return notesByAuthor;
//    }
//}
