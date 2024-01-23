package logic.com.example.StudySwap.connection.Connection;

import logic.com.example.StudySwap.connection.engineering.Printer;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;


//questa classe implementa un pattern Singleton, garantendo che venga creata solo un'istanza di Conncetion
//e che questa venga condivisa tra tutti coloro che richiedono una connessione
public class ConnectionDB {
    //costruttore privato per impedire l'istaziazione diretta della classe da parte di altri oggetti
    private ConnectionDB(){

    }

    //variabile statica che contiene l'unica istanza di connessione disponibile
    private static Connection connection;

    //metodo che restituisce l'istanza della connessione
    //se non è stata ancora creata, carica le informazioni di configurazione del file db.properties, carica il driver JDBC e successivamente la connessione
    public static Connection getConnection() {
        String user;
        String password;
        String url;
        String driverClassName;

        if (connection == null) {
            try {
                Properties db = loadProperties();
                url = db.getProperty("CONNECTION_URL");
                driverClassName = db.getProperty("DRIVER_CLASS_NAME");
                user = db.getProperty("USER");
                password = db.getProperty("PASS");
                Class.forName(driverClassName);

                connection = DriverManager.getConnection(url,user, password);

            }catch(SQLException | IOException | ClassNotFoundException e){
                Printer.printError(e.getMessage());
            }
        }

        return connection;
    }

    //chiude la connessione
    public static void closeConnection() throws SQLException{
        connection.close();
    }

    //metodo che carica le informazioni di configurazione del file utilizzando l'oggetto Properties
    //esso contiene: URL db, nome del driver, username e password per accedere al db
    private static Properties loadProperties() throws IOException{
        Properties properties = new Properties();

        try(FileInputStream fileInputStream = new FileInputStream("src/main/java/logic.com.example.StudySwap.connection/db.properties")){
            properties.load(fileInputStream);
        }

        return properties;
    }
}
