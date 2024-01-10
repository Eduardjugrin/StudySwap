import DAO.LoginDAO;
import DAO.LoginDAOCSV;
import DAO.LoginDAOJDBC;
import exception.AuthorNotFoundException;
import exception.UserNotFoundException;
import model.Note;
import model.User;
import DAO.NoteDAOCSV;

import java.sql.*;
import java.util.List;

public class Main{
    private static final String CONNECTION_URL = "jdbc:mysql://localhost:3306/study_swap_db";
    private static final String USER = "StudySwap";
    private static final String PASS = "StudySwap";
/*    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Hello World!");

        Button btn = new Button("Say 'Hello World'");
        btn.setOnAction(e -> System.out.println("Hello World!"));

        StackPane root = new StackPane();
        root.getChildren().add(btn);

        primaryStage.setScene(new Scene(root, 300, 250));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }*/
    //test login con csv
    public static void main(String[] args) {
        // Crea un'istanza di LoginDAOCSV
        LoginDAOCSV loginDAOCSV = new LoginDAOCSV();

        // Utente di test
        String testEmail = "test";
        String testPassword = "testpassword";

        try {
            // Prova ad autenticare l'utente
            User authenticatedUser = loginDAOCSV.checkUser(testEmail, testPassword);

            // Stampa informazioni sull'utente autenticato
            System.out.println("User authenticated successfully");
            System.out.println("Email: " + authenticatedUser.getEmail());
            System.out.println("Role: " + authenticatedUser.getRole());

        } catch (UserNotFoundException e) {
            // Gestisci il caso in cui l'utente non viene trovato
            System.out.println("User not found. Authentication failed.");
        }

        System.out.println("-*-*-*-*-*-*-*");

        //test notes con csv

        NoteDAOCSV noteDAOCSV = new NoteDAOCSV();

        List<Note> allNotes = noteDAOCSV.getAllNotes();

        if(allNotes.isEmpty()){
            System.out.println("Nessun appunto trovato");
        }else{
            System.out.println("Elenco appunti:");
            for(Note note: allNotes){
                System.out.println("Titolo:" + note.getTitle());
                System.out.println("DataPath:" + note.getPath());
                System.out.println("Autore: " + note.getAuthor());
                System.out.println("Prezzo: €" + note.getPrice());
                System.out.println("-----");
            }
        }
        System.out.println("-*-*-*-*-*-*-*");

        //test notes per autore con csv

        String AUTHOR = "Simone Niro";
        System.out.println("Libri di: " + AUTHOR);
        NoteDAOCSV noteDAO = new NoteDAOCSV();
        try {
            // Chiamata al metodo getNotesByAuthor per ottenere le note di un autore specifico
            List<Note> notes = noteDAO.getNotesByAuthor(AUTHOR);

            // Stampa delle note ottenute
            for (Note note : notes) {
                System.out.println("Title: " + note.getTitle());
             /*   System.out.println("Path: " + note.getPath());
                System.out.println("Author: " + note.getAuthor());
                System.out.println("Price: " + note.getPrice());
                System.out.println("---------------");*/
            }
        } catch (AuthorNotFoundException e) {
            // Gestione dell'eccezione se le note non sono trovate
            System.out.println("Author not found");
        }


        System.out.println("TEST DB:");
        String email = "bob.jones@example.com";
        String password = "pass123";

        // Crea un'istanza del tuo DAO JDBC
        LoginDAO loginDAOJDBC = new LoginDAOJDBC();

        try {
            // Esegui il check dell'utente
            User user = loginDAOJDBC.checkUser(email, password);

            // Stampa le informazioni sull'utente
            if (user != null) {
                System.out.println("User found:");
                System.out.println("Email: " + user.getEmail());
                System.out.println("Role: " + user.getRole());
            } else {
                System.out.println("User not found.");
            }
        } catch (UserNotFoundException e) {
            System.out.println("User not found.");
        }
    }
    }