package airbnb.clone.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
@ToString
public class User {

    private int userId = 1;

    private String name;
    private String email;
    private String birthDay;
    private String phoneNumber;
    private List<Room> rooms;

    public User(String name, String email, String birthDay, String phoneNumber, List<Room> rooms) {
        this.userId = userId++;
        this.name = name;
        this.email = email;
        this.birthDay = birthDay;
        this.phoneNumber = phoneNumber;
        this.rooms = rooms;
    }

    public void addRooms(Room room) {
        rooms.add(room);
    }
}
