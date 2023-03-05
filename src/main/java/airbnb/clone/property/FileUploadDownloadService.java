package airbnb.clone.property;


import airbnb.clone.exception.CustomException;
import airbnb.clone.exception.ErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FileUploadDownloadService {

    private final Path fileLocation;

    //디렉토리 설정 및 생성 코드
    @Autowired
    public FileUploadDownloadService(FileUploadProperties prop) {
        this.fileLocation = Paths.get(prop.getUploadDir()).toAbsolutePath().normalize();
        try {
            Files.createDirectories(this.fileLocation);
        } catch (IOException e) {
            throw new CustomException(ErrorCode.NOT_DIRECTORY);
        }
    }

    //파일 저장 코드
    public String storeFile(MultipartFile file) {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        try {
            if (fileName.contains("..")) {
                throw new CustomException(ErrorCode.NOT_ACCEPTED_MESSAGE);
            }

            //resolve : converts a given path string to a Path and resolves it against this Path in the exact same manner as specified by the resolve method.
            Path targetLocation = this.fileLocation.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
            return fileName;
        } catch (IOException e) {
            throw new CustomException(ErrorCode.NOT_UPLOAD);
        }
    }

    //파일 다운로드 소스
    public Resource loadFileAsResource(String fileName) {
        try {
            Path filePath = this.fileLocation.resolve(fileName).normalize();
            //Create a new UrlResource based on the given URI object.
            Resource resource = new UrlResource(filePath.toUri());

            if (resource.exists()) {
                return resource;
            } else {
                throw new CustomException(ErrorCode.NOT_FOUND_FILE);
            }
        } catch (MalformedURLException e) {
            throw new CustomException(ErrorCode.NOT_FOUND_FILE);
        }
    }
}
