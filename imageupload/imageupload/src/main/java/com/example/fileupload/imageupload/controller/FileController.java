package com.example.fileupload.imageupload.controller;


import java.util.Optional;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.fileupload.imageupload.Model.FileEntity;
import com.example.fileupload.imageupload.service.FileService;

@RestController
@RequestMapping("/api/files")
public class FileController {
	private FileService fileService;

	public FileController(FileService fileService) {
		this.fileService = fileService;
	}
	@PostMapping("/upload")
	public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file){
		try {
			FileEntity savedFile = fileService.storeFile(file);
			return ResponseEntity.ok("file uploaded successfully: "+ savedFile.getId());
		}catch (Exception e) {
			return ResponseEntity.status(500).body("File upload failed: "+ e.getMessage());
		}
		
	}
	@GetMapping("/download/{id}")
	public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable Long id) {
	    Optional<FileEntity> optionalFile = fileService.getFile(id);
	    if (optionalFile.isPresent()) {
	        FileEntity file = optionalFile.get();
	        return ResponseEntity.ok()
	            .contentType(MediaType.parseMediaType(file.getType()))
	            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getName() + "\"")
	            .body(new ByteArrayResource(file.getData()));
	    } else {
	        return ResponseEntity.notFound().build();
	    }
	}


}
