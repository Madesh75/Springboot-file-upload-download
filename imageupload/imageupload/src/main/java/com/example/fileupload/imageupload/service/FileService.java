package com.example.fileupload.imageupload.service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.fileupload.imageupload.Model.FileEntity;
import com.example.fileupload.imageupload.repository.FileRepository;

@Service
public class FileService {
	private final FileRepository fileRepository;

	public FileService(FileRepository fileRepository) {
		this.fileRepository = fileRepository;
	}
	public FileEntity storeFile(MultipartFile file) throws Exception{
		FileEntity fileEntity = new FileEntity();
		fileEntity.setName(file.getOriginalFilename());
		fileEntity.setType(file.getContentType());
		fileEntity.setData(file.getBytes());
		
		return fileRepository.save(fileEntity);
	}
	
	public Optional<FileEntity> getFile(Long id){
		return fileRepository.findById(id);
	}

}
