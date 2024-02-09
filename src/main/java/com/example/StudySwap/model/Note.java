package com.example.StudySwap.model;

public class Note {
    private int fileID;
    private String fileName;
    private String extension;
    private byte[] content;
    private String uploaderEmail;
    private double price;
    private String subject;
    private String author;

    //necessario per le operazioni di recupero dal db
    public Note(String fileName, String extension, byte[] content, String uploaderEmail, double price, String subject, String author){
        this.fileName = fileName;
        this.extension = extension;
        this.content = content;
        this.uploaderEmail = uploaderEmail;
        this.price = price;
        this.subject = subject;
        this.author = author;
    }

    //altro costruttore
//    public Note(String fileName, String extension, byte[] content, String uploaderEmail, double price, String subject){
//        this.fileName = fileName;
//        this.extension = extension;
//        this.content = content;
//        this.uploaderEmail = uploaderEmail;
//        this.price = price;
//        this.subject = subject;
//    }

    public double getPrice() {
        return price;
    }

    public String getSubject() {
        return subject;
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

    public String getAuthor() {
        return author;
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

    public void setPrice(double price) {
        this.price = price;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
