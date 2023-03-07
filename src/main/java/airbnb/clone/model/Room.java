package airbnb.clone.model;

import lombok.*;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Builder
public class Room {
    private int roomId;

    @NotBlank
    private String roomName;

    @NotBlank
    private String location;
    private int ownerId;

    private String roomPhoto;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Room() {
    }

    public Room(int roomId, String roomName, String location, int ownerId, String roomPhoto, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.roomId = roomId;
        this.roomName = roomName;
        this.location = location;
        this.ownerId = ownerId;
        this.roomPhoto = roomPhoto;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Room(Room room, int ownerId) {
        this.roomName = room.roomName;
        this.location = room.location;
        this.ownerId = ownerId;
        this.createdAt = LocalDateTime.now();
    }
}
