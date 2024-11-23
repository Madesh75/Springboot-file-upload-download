package com.example.fileupload.imageupload.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.fileupload.imageupload.Model.FileEntity;

public interface FileRepository extends JpaRepository<FileEntity,Long>{

}
