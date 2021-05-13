package com.lookup.backend.lookupbackend.service.fileservice;

import com.lookup.backend.lookupbackend.model.filemodel.FileModel;
import com.lookup.backend.lookupbackend.repository.FileRepository;
import com.lookup.backend.lookupbackend.service.fileservice.FileService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@NoArgsConstructor
public class FileServiceImpl implements FileService {

    @Autowired
    private FileRepository fileRepository;

    public void save(MultipartFile file) throws IOException {
        FileModel fileEntity = new FileModel();
        fileEntity.setName(StringUtils.cleanPath(file.getOriginalFilename()));
        fileEntity.setContentType(file.getContentType());
        fileEntity.setData(file.getBytes());
        fileEntity.setSize(file.getSize());

        fileRepository.save(fileEntity);
    }

    public Optional<FileModel> getFile(String id) {
        return fileRepository.findById(id);
    }

    public List<FileModel> getAllFiles() {
        return fileRepository.findAll();
    }
}
