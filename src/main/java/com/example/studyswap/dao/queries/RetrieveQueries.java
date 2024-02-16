package com.example.studyswap.dao.queries;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

//questa classe conterrà vari metodi statici per eseguire le queries in SQL
public class RetrieveQueries {

    //variabile statica che contiene l'istanza di PreparedStatement
    private static PreparedStatement preparedStatement = null;

    //cotruttore privato per evitare che venga istanziata da parte di altri oggetti
    private RetrieveQueries(){

    }

    //riceve una connessione al db, email e password come parametri.
    //crea una query SQL parametrica per selezionare il ruolo dell'utente dalla tabella users in base ai parametri forniti
    //TYPE_SCROLL_INSENSITIVE e CONCUR_READ_ONLY consentono l'accesso alla versione scorrevole e non modificabile del risultato della query
    public static ResultSet checkUser(Connection connection, String email, String password) throws SQLException {
        //query che seleziona la colonna role dalla tabella user dove email e password corrispondono ai valori
        //specificati nei parametri della query
        String sql = "SELECT role FROM users WHERE email = ? AND password = ?";

        preparedStatement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

        preparedStatement.setString(1, email);
        preparedStatement.setString(2, password);

        //esegue la query sulla connessione fornita e restituisce il risultato come ResultSet
        return preparedStatement.executeQuery();
    }

    public static ResultSet retrieveBuyerByEmail(Connection connection, String email) throws SQLException{

        String sql = "SELECT u.email, u.firstName, u.lastName, b.paymentMethod " +
                "FROM users u " +
                "JOIN buyers b ON u.email = b.email " +
                "WHERE u.email = ?";

        preparedStatement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

        preparedStatement.setString(1, email);

        return preparedStatement.executeQuery();
    }

    public static ResultSet retrieveSellerByEmail(Connection connection, String email) throws SQLException{
        String sql = "SELECT u.email, u.firstName, u.lastName, s.bankAccountDetails " +
                "FROM users u " +
                "JOIN sellers s ON u.email = s.email " +
                "WHERE u.email = ?";
        preparedStatement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

        preparedStatement.setString(1, email);

        return preparedStatement.executeQuery();
    }

    public static ResultSet retrieveAllNotes(Connection connection) throws SQLException{
        String sql = "SELECT f.*, u.firstName, u.lastName " +
                "FROM files f " +
                "INNER JOIN users u ON f.uploaderEmail = u.email";

        preparedStatement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

        return preparedStatement.executeQuery();
    }

    public static ResultSet retrieveNotesBySeller(Connection connection, String sellerEmail) throws SQLException{
        String sql = "SELECT * FROM files WHERE uploaderEmail = ?";

        preparedStatement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

        preparedStatement.setString(1, sellerEmail);

        return preparedStatement.executeQuery();
    }

    public static ResultSet retrieveUser(Connection connection, String sellerEmail) throws SQLException{
        String sql = "SELECT firstName, lastName FROM users WHERE email = ?";

        preparedStatement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

        preparedStatement.setString(1, sellerEmail);

        return preparedStatement.executeQuery();
    }

    public static ResultSet retrieveAllPurchasedNotes(Connection connection, String buyerEmail) throws SQLException{
        String sql = "SELECT f.* , u.firstName, u.lastName " +
                "FROM purchased p " +
                "JOIN files f ON p.fileId=f.id " +
                "JOIN users u on f.uploaderEmail = u.email " +
                "WHERE p.buyerEmail = ? ";

        preparedStatement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

        preparedStatement.setString(1, buyerEmail);

        return preparedStatement.executeQuery();
    }

    public static ResultSet retrieveFile(Connection connection, int fileId) throws SQLException{
        String sql = "SELECT content FROM files WHERE id = ?";

        preparedStatement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

        preparedStatement.setInt(1, fileId);

        return preparedStatement.executeQuery();

    }
}