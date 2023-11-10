package com.ra.serviceImp;

import com.google.cloud.storage.*;
import com.ra.service.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.ServletContext;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


@Service
public class FileStorageServiceImp implements FileStorageService {
    @Autowired
    private ServletContext servletContext;
    @Autowired
    private Storage storage;
    private final String BUCK_NAME = "fir-demo-1eec4.appspot.com";

    @Override
    public String uploadFile(MultipartFile multipartFile) {
        String pathUpload = servletContext.getRealPath("/");
        File uploadFolder = new File(pathUpload+"/uploads");
        if (!uploadFolder.exists()){
            uploadFolder.mkdirs();
        }
        String fileName = multipartFile.getOriginalFilename();
        File fileUpload = new File(uploadFolder+File.separator+fileName);

        try {
            FileCopyUtils.copy(multipartFile.getBytes(), fileUpload);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return uploadFileToFirebase(uploadFolder+File.separator+fileName);
    }

    @Override
    public String uploadFileToFirebase(String localFilePath) {
        Path localPath = Paths.get(localFilePath);
        String fileName = localPath.getFileName().toString();

        BlobId blobId = BlobId.of(BUCK_NAME, fileName);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).build();
        // Thiết lập quyền truy cập công cộng
        List<Acl> acls = new ArrayList<>();
        acls.add(Acl.of(Acl.User.ofAllUsers(), Acl.Role.READER));
        blobInfo = blobInfo.toBuilder().setAcl(acls).build();
        try {
            Blob blob = storage.create(blobInfo, Files.readAllBytes(localPath));
            return blob.getMediaLink();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
