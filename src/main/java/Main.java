import Model.Note;
import Model.NoteContainer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.util.List;

public class Main extends Application {

    @Override
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
    }
    /*


    static NoteContainer noteContainer = new NoteContainer();
    public static void main(String[] args) {
        Note note1 = new Note("titolo1", "content1", 1);
        Note note2 = new Note( "titolo2", "content2", 2);

        noteContainer.addNote(note1);
        noteContainer.addNote(note2);

        //ottieni e visualizza gli appunti
        List<Note> allNotes = noteContainer.getNotes();
        for(Note note : allNotes){
            System.out.println("Titolo: " + note.getTitle());
            System.out.println("Contenuto: " + note.getContent());
            System.out.println("Utente ID: " + note.getUserId());
            System.out.println("----");
        }
*/
}