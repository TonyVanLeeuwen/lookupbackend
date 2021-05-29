package com.lookup.backend.lookupbackend.service.fileservice;

import com.lookup.backend.lookupbackend.model.filemodel.FileModel;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface FileService {

    public FileModel save(MultipartFile file) throws IOException;
    public Optional<FileModel> getFile(String id);
    public List<FileModel> getAllFiles();

}
