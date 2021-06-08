package com.lookup.backend.lookupbackend.repository;

import com.lookup.backend.lookupbackend.model.filemodel.FileModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepository extends JpaRepository<FileModel, String> {
}
