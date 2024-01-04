package Model;

public class User {
    private String firstName;
    private String lastName;
    private String username;
    private int id;

    public User(String firstName, String lastName, String username, int id){
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.id = id;
    }

    public String getUsername(){
        return(this.username);
    }

    public String getFirstName(){
        return(this.firstName);
    }

    public String getLastName(){
        return(this.lastName);
    }
}

