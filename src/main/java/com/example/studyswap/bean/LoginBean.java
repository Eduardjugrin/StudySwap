package com.example.studyswap.bean;

import com.example.studyswap.exception.EmailFormatException;

import java.util.regex.Pattern;


public class LoginBean{
    private String email;
    private String password;
    private int role;

    public LoginBean(String email, String password) throws EmailFormatException{
        this.setEmail(email);
        this.setPassword(password);
    }

    public String getEmail(){
        return email;
    }

    public String getPassword(){
        return password;
    }

    public int getRole(){
        return role;
    }

    public void setEmail(String email) throws EmailFormatException{
        //la REX verifica se la stringa email corrisponde al formato previsto per un indirizzo email
        String emailRegex = "[a-zA-Z0-9._%-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}";
        if(!Pattern.compile(emailRegex).matcher(email).matches()) {
            throw new EmailFormatException(email);
        }
        this.email = email;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public void setRole(int role){
        this.role = role;
    }
}
