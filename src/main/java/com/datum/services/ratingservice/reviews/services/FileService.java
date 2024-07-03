package com.datum.services.ratingservice.reviews.services;

import com.datum.services.ratingservice.reviews.dtos.FileDto;
import io.minio.*;
import io.minio.errors.MinioException;
import io.minio.http.Method;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

@Service
public class FileService {
//    @Value("${minio.bucketName}")
    private String bucketName;
//    @Value("${minio.expiresInMinutes}")
    private Integer expiresInMinutes;

    private final MinioClient minioClient= null;

    public FileService() {
//        this.minioClient = minioClient;
//        this.minioClient.setTimeout( 1000, 60 * 60 * 1000, 60 * 60 * 1000);
//        createBucket();
    }


    private void createBucket() {
        try {
            boolean bucketExists = minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
            if (!bucketExists) minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
        } catch (MinioException | IOException | NoSuchAlgorithmException | InvalidKeyException e) {
            throw new RuntimeException(e);
        }
    }

    public FileDto uploadFile(MultipartFile file) {
        try {
            String fileName = UUID.randomUUID().toString();
            String extension = file.getOriginalFilename() == null ? "" : file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1);
            fileName = fileName + "." + extension;
            minioClient.putObject(PutObjectArgs.builder().bucket(bucketName).object(fileName).stream(file.getInputStream(), file.getSize(), -1).contentType(file.getContentType()).build());
            return FileDto.builder()
                    .name(fileName)
                    .url(getURL(fileName))
                    .type(file.getContentType())
                    .build();
        } catch (Exception e) {
            throw new RuntimeException("Error uploading file: " + e.getMessage());
        }
    }


    public String getURL(String fileName) {
        try {
            return minioClient.getPresignedObjectUrl(GetPresignedObjectUrlArgs.builder().bucket(bucketName).object(fileName).expiry(expiresInMinutes * 60).method(Method.GET).build());
        } catch (Exception e) {
            System.out.println("e = " + e.getMessage());
            return "https://file-examples.com/storage/fe3b4f721f64dfeffa49f02/2017/04/file_example_MP4_480_1_5MG.mp4";
        }
    }

    public static String URL(String fileName) {
        MinioClient minioClient = MinioClient.builder().endpoint("http://164.68.104.208:9000").credentials("SxEdVzYvtcS2s7krDuZM", "RIXqfmG1ppV0seuxukYaYWs78zTzSPPMyqM2eQBi").build();
        try {
            return minioClient.getPresignedObjectUrl(GetPresignedObjectUrlArgs.builder().bucket("data").object(fileName).expiry(60 * 60).method(Method.GET).build());
        } catch (Exception e) {
            return null;
        }
    }

    public Boolean deleteFile(String video) {
        try {
            minioClient.removeObject(RemoveObjectArgs.builder().bucket(bucketName).object(video).build());
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
