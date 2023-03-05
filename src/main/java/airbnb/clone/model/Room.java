package airbnb.clone.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
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

    public Room(Room room, int ownerId) {
        this.roomName = room.roomName;
        this.location = room.location;
        this.ownerId = ownerId;
        this.createdAt = LocalDateTime.now();
    }
}
