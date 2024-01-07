import DAO.LoginDAOCSV;
import exception.AuthorNotFoundException;
import exception.UserNotFoundException;
import model.Note;
import model.User;
import DAO.NoteDAOCSV;

import java.util.List;

public class Main{

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
        LoginDAOCSV loginDAO = new LoginDAOCSV();

        // Utente di test
        String testEmail = "test";
        String testPassword = "testpassword";

        try {
            // Prova ad autenticare l'utente
            User authenticatedUser = loginDAO.checkUser(testEmail, testPassword);

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

    }
}