package airbnb.clone.model;

import lombok.*;

@Getter
@Setter
@RequiredArgsConstructor
@ToString
public class Room {
    private int roomId;
    private String location;
    private User owner;

}
