package com.example.studyswap.dao;

import com.example.studyswap.exception.UserNotFoundException;
import com.example.studyswap.model.User;

//interfaccia per il logic.com.example.StudySwap.connection.DAO relativo al login
public interface LoginDAO {

    //verifica credenziali dell'utente e restituisce il profilo associato
    User checkUser(String email, String password) throws UserNotFoundException;
}
