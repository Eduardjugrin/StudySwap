package com.example.studyswap.engineering.factory;

import com.example.studyswap.DAO.LoginDAO;
import com.example.studyswap.DAO.LoginDAOJDBC;

//factory per creare istanze di oggetti LoginDAO
public class LoginDAOFactory {

    //costruttore privato per impedire istanze esterne
    private LoginDAOFactory(){
    }

    private static LoginDAOFactory instance = null;

    //restituisce l'istanza factory, se essa non esiste viene creata
    public static LoginDAOFactory getInstance(){
        if(instance == null){
            instance = new LoginDAOFactory();
        }
        return instance;
    }

    //crea e restituisce un'istanza di LoginDAO [return LoginDAOCSV(); return LiginDAOJDBC;]
    public LoginDAO createLoginDAO(){
        return new LoginDAOJDBC();
    }
}
