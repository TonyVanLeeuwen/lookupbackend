package com.lookup.backend.lookupbackend.service;

import com.lookup.backend.lookupbackend.model.filemodel.FileModel;
import com.lookup.backend.lookupbackend.repository.FileRepository;
import com.lookup.backend.lookupbackend.service.fileservice.FileService;
import com.lookup.backend.lookupbackend.service.fileservice.FileServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class FileServiceTest {

    private MockMvc mockMvc;

    @InjectMocks
    private final FileService fileService = new FileServiceImpl();

    @Mock
    private FileRepository fileRepository;
    private final FileModel FileModel = new FileModel();

    @Test
    void fileServiceNotNull(){
        Assertions.assertNotNull(fileService);
    }

    @Test
    void FileRepositoryNotNull(){
        Assertions.assertNotNull(fileRepository);
    }

    @Test
    void getFileRequestShouldReturnOptionalOfFile(){
        //Arrange
        FileModel fileModel = new FileModel();
        fileModel.setName("file");
        when(fileService.getFile("file")).thenReturn(java.util.Optional.ofNullable((FileModel)));
        //Act
        Optional<FileModel> fileModelOptional = fileService.getFile("file");
        //Assert
        Assertions.assertEquals(java.util.Optional.of(FileModel), fileModelOptional);
    }

    @Test
    void getAllFilesRequestShouldReturnListOfALlFiles() {
        FileModel fileModelOne = new FileModel();
        FileModel fileModelTwo = new FileModel();
        fileRepository.save(fileModelOne);
        fileRepository.save(fileModelTwo);
        when(fileService.getAllFiles()).thenReturn(List.of(FileModel));

        List<FileModel> listOfFiles = fileService.getAllFiles();

        Assertions.assertNotNull(listOfFiles);
        Assertions.assertFalse(listOfFiles.isEmpty());
    }

    @Test
    void saveRequestShouldReturnFileModel() throws Exception {
        MockMultipartFile multipartFile = new MockMultipartFile("filename", "filename", "type", (byte[]) null);

        FileModel fileModel = fileService.save(multipartFile);

        Assertions.assertSame(fileModel.getClass(), FileModel.getClass());
    }

}
