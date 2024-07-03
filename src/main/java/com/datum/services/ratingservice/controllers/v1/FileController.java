package com.datum.services.ratingservice.controllers.v1;

import com.datum.services.ratingservice.reviews.dtos.FileDto;
import com.datum.services.ratingservice.reviews.services.FileService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class FileController {

    private final FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }


    @PostMapping(path = "/upload", consumes = {"multipart/form-data"})
    public FileDto uploadFile(@RequestParam("file") MultipartFile file) {
        return fileService.uploadFile(file);
    }
}
