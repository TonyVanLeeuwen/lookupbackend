package com.lookup.backend.lookupbackend.controller;


import com.lookup.backend.lookupbackend.model.filemodel.FileModel;
import com.lookup.backend.lookupbackend.model.filemodel.FileResponse;
import com.lookup.backend.lookupbackend.model.observationmodel.Observation;
import com.lookup.backend.lookupbackend.model.usermodel.User;
import com.lookup.backend.lookupbackend.service.fileservice.FileService;
import com.lookup.backend.lookupbackend.service.userservice.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class FileModelController {

    @Autowired
    private UserService userService;

    @Autowired
    private FileService fileService;

    @PostMapping(value = "/users/uploadobservationwithimage")
    public ResponseEntity<Object> uploadFile(@RequestParam("file") MultipartFile file, Principal principal){
        try{
            User user = userService.getUserByName(principal.getName());
            Observation observation = new Observation();

            observation.setTitle(file.getName());

            FileModel fileModel = fileService.save(file);

            observation.addFile(fileModel);

            observation.setPictureDescriptionURL(mapToFileResponse(fileModel).getUrl());

            user.addObservation(observation);

            userService.updateUser(user.getName(), user);

            return new ResponseEntity<>("succes!", HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity<>("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/files/allfiles")
    public List<FileResponse> list() {
        return fileService.getAllFiles()
                .stream()
                .map(this::mapToFileResponse)
                .collect(Collectors.toList());
    }

    private FileResponse mapToFileResponse(FileModel fileEntity) {
        String downloadURL = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/getfile/")
                .path(fileEntity.getId())
                .toUriString();
        FileResponse fileResponse = new FileResponse();
        fileResponse.setId(fileEntity.getId());
        fileResponse.setName(fileEntity.getName());
        fileResponse.setContentType(fileEntity.getContentType());
        fileResponse.setSize(fileEntity.getSize());
        fileResponse.setUrl(downloadURL);

        return fileResponse;
    }

    @GetMapping(value = "/getfile/{id}")
    public ResponseEntity<byte[]> getFile(@PathVariable String id){
        Optional<FileModel> fileModelOptional = fileService.getFile(id);

        if (!fileModelOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        FileModel fileModel = fileModelOptional.get();
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileModel.getName() + "\"")
                .contentType(MediaType.valueOf(fileModel.getContentType()))
                .body(fileModel.getData());
    }
}

