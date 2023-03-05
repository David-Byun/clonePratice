package airbnb.clone.controller;

import airbnb.clone.model.Room;
import airbnb.clone.property.FileUploadDownloadService;
import airbnb.clone.property.FileUploadResponse;
import airbnb.clone.service.RoomManageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/api")
public class RoomController {
    private RoomManageService roomManageService;
    private FileUploadDownloadService fileService;

    @Autowired
    public RoomController(RoomManageService roomManageService, FileUploadDownloadService fileService) {
        this.roomManageService = roomManageService;
        this.fileService = fileService;
    }

    @PostMapping("/room/user/{userId}")
    public FileUploadResponse createRoom(@RequestPart Room room, @RequestPart MultipartFile file, @PathVariable Integer userId) {
        String fileName = fileService.storeFile(file);
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/room/downloadFile/").path(fileName).toUriString();
        room.setRoomPhoto(fileDownloadUri);
        room.setOwnerId(userId);
        log.info("룸 확인 = {}", room);
        roomManageService.registerRoom(room, userId);
        return new FileUploadResponse(fileName, fileDownloadUri, file.getContentType(), file.getSize());
    }

    @GetMapping("/room/downloadFile/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) throws IOException {
        // Load file as Resource
        Resource resource = fileService.loadFileAsResource(fileName);

        // Try to detemine file`s content type
        String contentType = null;
        contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        if (contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType)).header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename()).body(resource);
    }

}



























