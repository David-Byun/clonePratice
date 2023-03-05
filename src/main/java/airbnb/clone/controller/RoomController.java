package airbnb.clone.controller;

import airbnb.clone.exception.CustomException;
import airbnb.clone.exception.ErrorCode;
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
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/api")
public class RoomController {
    private final RoomManageService roomManageService;
    private final FileUploadDownloadService fileService;

    @Autowired
    public RoomController(RoomManageService roomManageService, FileUploadDownloadService fileService) {
        this.roomManageService = roomManageService;
        this.fileService = fileService;
    }

    @GetMapping("/room")
    public List<Room> findAllRooms() {
        return roomManageService.findAllRooms();
    }

    @GetMapping("/room/{roomId}")
    public Optional<Room> findRoom(@PathVariable int roomId) {
        return Optional.ofNullable(roomManageService.findRoomById(roomId).orElseThrow(() -> new CustomException(ErrorCode.NO_ROOM)));
    }

    @PostMapping("/room/{roomId}/delete")
    public ResponseEntity<String> deleteRoom(@PathVariable int roomId) {
        roomManageService.deleteRoom(roomId);
        return ResponseEntity.ok().body(roomId + "번 방이 삭제되었습니다");
    }

    @PostMapping("/room/{roomId}/update")
    public ResponseEntity<Room> updateRoom(@PathVariable("roomId") int roomId, @RequestBody Room room)
    {
        roomManageService.updateRoom(roomId, room);
        return ResponseEntity.ok().body(room);
    }

    @PostMapping("/room/user/{userId}")
    public FileUploadResponse createRoom(@RequestPart Room room, @RequestPart MultipartFile file, @PathVariable Integer userId) {
        String fileName = fileService.storeFile(file);
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/room/downloadFile/").path(fileName).toUriString();
        room.setRoomPhoto(fileDownloadUri);
        room.setOwnerId(userId);
        log.info("생성된 룸 = {}", room);
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



























