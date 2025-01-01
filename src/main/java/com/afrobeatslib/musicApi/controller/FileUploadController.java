package com.afrobeatslib.musicApi.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.afrobeatslib.musicApi.service.BlobStorageService;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@CrossOrigin(origins = "*")
public class FileUploadController {

    @Autowired
    private BlobStorageService blobStorageService;
    private String publicUrl;

    @PostMapping("/api/upload")
    public Map<String, String> uploadFile(@RequestParam("file") MultipartFile file) {
        Map<String, String> data = new HashMap<>();
        ResponseEntity<String> response;
        try {

            String extension = StringUtils.getFilenameExtension(file.getOriginalFilename());
            String blobName = UUID.randomUUID().toString() + "." + extension;
            String fullUrl = blobStorageService.uploadFile(file, blobName, "images");

            this.setPublicUrl(fullUrl);

            response = ResponseEntity.ok("Successesfully uploaded.");

            data.put("url", this.getPublicUrl());
            data.put("response", response.getStatusCode().toString());
            data.put("message", response.getBody());

            return data;
        } catch (Exception e) {

            e.printStackTrace(); // For debugging
            response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to upload file: " + e.getMessage());

            data.put("response", response.getStatusCode().toString());
            data.put("message", response.getBody());

            return data;
        }
    }

    public String getPublicUrl() {
        return this.publicUrl;
    }

    public void setPublicUrl(String newUrl) {
        this.publicUrl = newUrl;
    }

}
