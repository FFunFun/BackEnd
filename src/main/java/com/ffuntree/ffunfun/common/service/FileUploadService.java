package com.ffuntree.ffunfun.common.service;

import com.ffuntree.ffunfun.common.data.FileProperty;
import jakarta.annotation.PostConstruct;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Base64;
import java.util.Objects;
import java.util.UUID;


@Service
public class FileUploadService {

    private Path fileStorageLocation;
    private final String filePath = "ffunfun";

    @PostConstruct
    public void postConstruct() {
        fileStorageLocation = Path.of(filePath).toAbsolutePath().normalize();
        try {
            Files.createDirectories(fileStorageLocation);
        } catch (Exception ex) {
            throw new RuntimeException("Could not create the directory where the uploaded files will be stored.", ex);
        }
    }

    public FileProperty saveFile(MultipartFile multipartFile) {

        String filename = System.currentTimeMillis() + "_" + UUID.randomUUID() + "." + extractExtension(Objects.requireNonNull(multipartFile.getOriginalFilename()));
        Path location = fileStorageLocation.resolve(filename);
        try {
            Files.copy(multipartFile.getInputStream(), location);
            return new FileProperty(location.toString());
        } catch (Exception e) {
            throw new RuntimeException("Could not store the file. Please try again!", e);
        }
    }

    public String extractExtension(String filename) {
        return filename.substring(filename.lastIndexOf(".") + 1);
    }

    public void deleteFile(String filePath) {
        try {
            Files.delete(Path.of(filePath));
        } catch (Exception e) {
            throw new RuntimeException("Could not delete the file. Please try again!", e);
        }
    }

    public void deleteFile(FileProperty fileProperty) {
        deleteFile(fileProperty.getFilename());
    }

    public FileProperty updateFile(FileProperty fileProperty, MultipartFile multipartFile) {
        deleteFile(fileProperty);
        return saveFile(multipartFile);
    }

    public byte[] loadFileAsByteArray(String fileName) {
        try {
            Path filePath = fileStorageLocation.resolve(fileName).normalize();
            return Files.readAllBytes(filePath);
        } catch (Exception e) {
            throw new RuntimeException("File not found " + fileName, e);
        }
    }

    public String loadFileAsBase64(String fileName) {
        try {
            Path filePath = fileStorageLocation.resolve(fileName).normalize();
            byte[] fileByteArray = Files.readAllBytes(filePath);
            return Base64.getEncoder().encodeToString(fileByteArray);
        } catch (Exception e) {
            throw new RuntimeException("File not found " + fileName, e);
        }
    }

    public UrlResource loadFileAsResource(String fileName) {
        try {
            Path filePath = fileStorageLocation.resolve(fileName).normalize();
            UrlResource resource = new UrlResource(filePath.toUri());
            if (resource.exists()) {
                return resource;
            } else {
                throw new FileNotFoundException("File not found " + fileName);
            }
        } catch (Exception e) {
            throw new RuntimeException("File not found " + fileName, e);
        }
    }

}
