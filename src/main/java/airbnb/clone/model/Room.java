package airbnb.clone.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@RequiredArgsConstructor
@ToString
public class Room {
    private int roomId;
    private String location;
    private User owner;
    private LocalDateTime insertTime;

}
