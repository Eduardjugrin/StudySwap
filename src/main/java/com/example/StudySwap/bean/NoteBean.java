package com.example.StudySwap.bean;

public class NoteBean {
    private String fileName;
    private String extension;
    private byte[] content;
    private String uploaderEmail;

    public NoteBean(String fileName, String extension, byte[] content, String uploaderEmail){
        this.fileName = fileName;
        this.extension = extension;
        this.content = content;
        this.uploaderEmail = uploaderEmail;
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
}
