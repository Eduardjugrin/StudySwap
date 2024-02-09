package com.example.StudySwap.DAO;

import com.example.StudySwap.Connection.ConnectionDB;
import com.example.StudySwap.DAO.queries.RetrieveQueries;
import com.example.StudySwap.engineering.observer.Printer;
import com.example.StudySwap.exception.DuplicateNoteException;
import com.example.StudySwap.exception.NotFoundException;
import com.example.StudySwap.model.Note;

import java.io.ByteArrayInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NoteDAOJDBC implements NoteDAO{
    private static final String FILE_NAME = "fileName";
    private static final String EXTENSION = "extension";
    private static final String CONTENT = "content";
    private static final String UPLOADER_EMAIL = "uploaderEmail";
    private static final String SUBJECT = "subject";
    private static final String PRICE = "price";

    public static void uploadFile(Note note, String sellerEmail) throws DuplicateNoteException {
        Connection connection;

        try{
            connection = ConnectionDB.getConnection();

            if(note == null){
                throw new IllegalArgumentException("Note object cannot be null");
            }
            if(note.getFileName() == null || note.getExtension() == null || note.getContent() == null){
                throw new IllegalArgumentException("Note object contain null values");
            }
            if(fileExists(connection, note.getFileName())){
                throw new DuplicateNoteException();
            }

            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO files (fileName, extension, content, uploaderEmail, price, subject) VALUES(?, ?, ?, ?, ?, ?)");
                preparedStatement.setString(1, note.getFileName());
                preparedStatement.setString(2, note.getExtension());
                preparedStatement.setBinaryStream(3, new ByteArrayInputStream(note.getContent()));
                preparedStatement.setString(4, sellerEmail);
                preparedStatement.setDouble(5, note.getPrice());
                preparedStatement.setString(6, note.getSubject().toLowerCase());

                preparedStatement.executeUpdate();
            }catch(SQLException e){
            Printer.printError("Error uploading file: " + e.getMessage());
        }
    }

    @Override
    public List<Note> getAllNotes() throws NotFoundException{

        List<Note> noteList = new ArrayList<>();
        Connection connection;
        Note note = null;

        try{

            connection = ConnectionDB.getConnection();

            ResultSet resultSet = RetrieveQueries.retrieveAllNotes(connection);

            if(!resultSet.first()){
                throw new NotFoundException("no notes found");
            }

            resultSet.first();
            do{
                note = setFileData(resultSet);
            }while(resultSet.next());

            noteList.add(note);

        }catch(SQLException e){
            Printer.printError(e.getMessage());
        }
        return noteList;
    }

    public static List<Note> getAllNotesBySeller(String sellerEmail) throws NotFoundException{
        Connection connection;
        List<Note> notesByAuthorList = new ArrayList<>();
        Note note = null;
        ResultSet resultSet = null;

        try{
            connection = ConnectionDB.getConnection();

            resultSet = RetrieveQueries.retrieveNotesBySeller(connection, sellerEmail);

            if(!resultSet.first()){
                return null;
            }
            resultSet.first();
            do{
                note = setFileData(resultSet);
                notesByAuthorList.add(note);
            }while(resultSet.next());

        }catch(SQLException e){
            Printer.printError(e.getMessage());
        }

        return notesByAuthorList;
    }
    public static Note setFileData(ResultSet resultSet) throws SQLException{
        String fileName = resultSet.getString(FILE_NAME);
        String extension = resultSet.getString(EXTENSION);
        byte[] content = resultSet.getBytes(CONTENT);
        String uploaderEmail = resultSet.getString(UPLOADER_EMAIL);
        double price = resultSet.getDouble(PRICE);
        String subject = resultSet.getString(SUBJECT);

        return new Note(fileName, extension, content, uploaderEmail, price, subject);
    }

    private static boolean fileExists(Connection connection, String fileName) throws SQLException{
        String sql = "SELECT COUNT(*) FROM files WHERE fileName = ?";

        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setString(1, fileName);
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                if(resultSet.next()){
                    int count = resultSet.getInt(1);
                    return count > 0;
                }
            }
        }
        return false;
    }
}
