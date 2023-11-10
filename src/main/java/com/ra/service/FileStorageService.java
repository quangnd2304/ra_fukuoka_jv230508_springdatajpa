package com.ra.service;

import com.google.cloud.storage.Storage;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;

public interface FileStorageService {
    String uploadFile(MultipartFile multipartFile);
    String uploadFileToFirebase(String localFilePath);
}
