package airbnb.clone.service;

import airbnb.clone.mapper.RoomMapper;
import airbnb.clone.model.Room;
import airbnb.clone.model.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class RoomManageService {

    private final RoomMapper roomMapper;
    @Autowired
    public RoomManageService(RoomMapper roomMapper) {
        this.roomMapper = roomMapper;
    }

    public void registerRoom(User user, Room room) {
        roomMapper.register(room);
        System.out.println(user);
    }

    public void deleteRoom(int id) {
        roomMapper.deleteById(id);
    }

    public void updateRoom(int id, Room room) {
        Room findRoom = roomMapper.findById(id);
        findRoom.setRoomId(room.getRoomId());
        findRoom.setOwner(room.getOwner());
        findRoom.setLocation(room.getLocation());
    }

}
