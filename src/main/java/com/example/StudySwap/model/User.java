package com.example.StudySwap.model;

public class User {
    private String firstName;
    private String lastName;
    private String email;
    private int role;           //1 : buyer, 2 : seller

    public User(String firstName, String lastName, String username){
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = username;
    }

    public User(String email, int role){
        this.email = email;
        this.role = role;
    }

    public String getEmail(){
        return(this.email);
    }

    public String getFirstName(){

        return(this.firstName);
    }

    public String getLastName(){

        return(this.lastName);
    }

    public int getRole(){
        return(this.role);
    }
}

