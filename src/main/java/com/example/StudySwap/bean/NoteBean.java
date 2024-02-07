package com.example.StudySwap.bean;

public class NoteBean {
    private String fileName;
    private String extension;
    private byte[] content;
    private String uploaderEmail;
    private double price;
    private String subject;

    public NoteBean(String fileName, String extension, byte[] content, String uploaderEmail, double price, String subject){
        this.fileName = fileName;
        this.extension = extension;
        this.content = content;
        this.uploaderEmail = uploaderEmail;
        this.price = price;
        this.subject = subject;
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

    public double getPrice() {
        return price;
    }

    public java.lang.String getSubject() {
        return subject;
    }
}
