package model;

public class Note {
    private int id;
    private String title;
    private String content;
    private int userId;

    //necessario per le operazioni di recupero dal db
    public Note(){

    }

    public Note ( String title, String content, int userId){
        this.title = title;
        this.content = content;
        this.userId = userId;
    }

    //metodi getter
    public int getId(){
        return (this.id);
    }

    public String getTitle(){
        return (this.title);
    }

    public String getContent(){
        return (this.content);
    }

    public int getUserId(){
        return (this.userId);
    }
}
