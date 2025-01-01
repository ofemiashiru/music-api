package com.afrobeatslib.musicApi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobServiceClient;

@Service
public class BlobStorageService {

    @Autowired
    private BlobServiceClient blobServiceClient;

    public String uploadFile(MultipartFile file, String blobName, String containerName) {
        try {
            BlobClient blobClient = blobServiceClient.getBlobContainerClient(
                    containerName)
                    .getBlobClient(blobName);

            blobClient.upload(file.getInputStream(), file.getSize(), false);

            return "https://musicfileuploads.blob.core.windows.net/" + containerName + "/" + blobName;

        } catch (Exception e) {

            e.printStackTrace();

            return null;
        }
    }

}
