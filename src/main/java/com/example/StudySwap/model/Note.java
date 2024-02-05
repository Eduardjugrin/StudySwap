package com.example.StudySwap.model;

public class Note {
    private int fileID;
    private String fileName;
    private String extension;
    private byte[] content;
    private String uploaderEmail;

    //necessario per le operazioni di recupero dal db
    public Note(String fileName, String extension, byte[] content, String uploaderEmail){
        this.fileName = fileName;
        this.extension = extension;
        this.content = content;
        this.uploaderEmail = uploaderEmail;
    }


    public int getFileID() {
        return fileID;
    }

    public String getFileName() {
        return fileName;
    }

    public String getExtension() {
        return extension;
    }

    public byte[] getContent() {
        return content;
    }

    public String getUploaderEmail() {
        return uploaderEmail;
    }

    public void setFileID(int fileID) {
        this.fileID = fileID;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public void setUploaderEmail(String uploaderEmail) {
        this.uploaderEmail = uploaderEmail;
    }
}
