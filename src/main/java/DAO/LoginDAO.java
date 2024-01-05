package DAO;

import exception.UserNotFoundException;
import model.User;

//interfaccia per il DAO relativo al login
public interface LoginDAO {

    //verifica credenziali dell'utente e restituisce il profilo associato
    User checkUser(String email, String password) throws UserNotFoundException;
}
