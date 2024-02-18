package com.example.studyswap.dao;


import com.example.studyswap.connection.ConnectionDB;
import com.example.studyswap.engineering.Printer;
import com.example.studyswap.model.Note;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

    public abstract List<Note> getPurchasedNotes(String buyerEmail);

    public Note getNoteById(int fileId) throws SQLException {
        Note note = null;

        Connection connection;

        ResultSet resultSet = null;

        try {
            connection = ConnectionDB.getConnection();
            String sql = "SELECT f.*, u.firstName, u.lastName " +
                    "FROM files f " +
                    "JOIN users u on f.uploaderEmail = u.email " +
                    "WHERE f.id = ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

                preparedStatement.setInt(1, fileId);

                resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    note = setFileData(resultSet);
                }
            }
        } catch (SQLException e) {
            Printer.printMessage(e.getMessage());
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
        }
        return note;
    }

    public static Note setFileData(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt(FILE_ID);
        String fileName = resultSet.getString(FILE_NAME);
        String extension = resultSet.getString(EXTENSION);
        byte[] content = resultSet.getBytes(CONTENT);
        double price = resultSet.getDouble(PRICE);
        String subject = resultSet.getString(SUBJECT);
        String author = resultSet.getString(FIRST_NAME) + " " + resultSet.getString(LAST_NAME);

        return new Note(id, fileName, extension, content, price, subject, author);
    }
}
