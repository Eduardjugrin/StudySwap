package logic.com.example.StudySwap.connection.DAO;

import logic.com.example.StudySwap.connection.exception.UserNotFoundException;
import logic.com.example.StudySwap.connection.model.User;

//interfaccia per il logic.com.example.StudySwap.connection.DAO relativo al login
public interface LoginDAO {

    //verifica credenziali dell'utente e restituisce il profilo associato
    User checkUser(String email, String password) throws UserNotFoundException;
}
