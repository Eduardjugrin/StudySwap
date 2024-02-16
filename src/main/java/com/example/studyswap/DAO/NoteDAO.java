package com.example.studyswap.DAO;


import com.example.studyswap.Connection.ConnectionDB;
import com.example.studyswap.DAO.queries.RetrieveQueries;
import com.example.studyswap.engineering.observer.Printer;
import com.example.studyswap.exception.NotFoundException;
import com.example.studyswap.model.Note;
import com.mysql.cj.jdbc.exceptions.NotUpdatable;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class NoteDAO {
    private static final String FILE_ID = "id";
    private static final String FILE_NAME = "fileName";
    private static final String EXTENSION = "extension";
    private static final String CONTENT = "content";
    private static final String UPLOADER_EMAIL = "uploaderEmail";
    private static final String SUBJECT = "subject";
    private static final String PRICE = "price";
    private static final String FIRST_NAME = "firstName";
    private static final String LAST_NAME = "lastName";

    //public List<Note> getAllNotes() throws NotFoundException;
    public abstract List<Note> getPurchasedNotes(String buyerEmail);

    public Note getNoteById(int fileId) throws SQLException {
        Note note = null;

        Connection connection;

        ResultSet resultSet = null;

        List<Note> notes = new ArrayList<>();

        try{
            connection = ConnectionDB.getConnection();
            String sql = "SELECT f.*, u.firstName, u.lastName " +
                    "FROM files f " +
                    "JOIN users u on f.uploaderEmail = u.email " +
                    "WHERE f.id = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, fileId);

            resultSet = preparedStatement.executeQuery();

            resultSet.next();
            note = setFileData(resultSet);

        }catch(SQLException e){
            Printer.printMessage(e.getMessage());
        }
        return note;
    }

    public static Note setFileData(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt(FILE_ID);
        String fileName = resultSet.getString(FILE_NAME);
        String extension = resultSet.getString(EXTENSION);
        byte[] content = resultSet.getBytes(CONTENT);
        String uploaderEmail = resultSet.getString(UPLOADER_EMAIL);
        double price = resultSet.getDouble(PRICE);
        String subject = resultSet.getString(SUBJECT);
        String author = resultSet.getString(FIRST_NAME) + " " + resultSet.getString(LAST_NAME);

        return new Note(id, fileName, extension, content, uploaderEmail, price, subject, author);
    }
}
