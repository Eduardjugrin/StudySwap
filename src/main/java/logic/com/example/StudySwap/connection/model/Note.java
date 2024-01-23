package logic.com.example.StudySwap.connection.model;

public class Note {
    private String title;
    private String path;
    private String author;
    private String price;

    //necessario per le operazioni di recupero dal db
    public Note(){

    }

    public Note ( String title, String path, String userEmail, String price){
        this.title = title;
        this.path = path;
        this.author = userEmail;
        this.price = price;
    }

    public Note(String title){
        this.title = title;
    }

    //metodi getter
    public String getTitle(){
        return (this.title);
    }

    public String getPath(){
        return (this.path);
    }

    public String getAuthor(){
        return (this.author);
    }
    public String getPrice(){return (this.price);}
}
