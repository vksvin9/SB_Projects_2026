package com.vin.dto;

public class FileResponse {

    private String fileName;
    private String downloadUrl;

    public FileResponse(String fileName, String downloadUrl) {
        this.fileName = fileName;
        this.downloadUrl = downloadUrl;
    }

    public String getFileName() {
        return fileName;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }
}