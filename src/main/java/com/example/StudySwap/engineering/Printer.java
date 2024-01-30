package com.example.StudySwap.engineering;

import java.util.logging.Logger;

//utility per la stampa di messaggi di errore
public class Printer {

    //Logger globale per la registrazione dei messaggi
    static Logger log = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    //costruttore privato per evitare istanze esterne
    private Printer(){

    }

    //stampa messaggio errore
    public static void printError(String error){
        log.info(error);
    }

    //stampa messaggio nella console standard di output
    public static void printMessage(String message){
        System.out.println(message);
    }
}
