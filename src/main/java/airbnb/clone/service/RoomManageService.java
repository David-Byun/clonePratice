package airbnb.clone.service;

import airbnb.clone.mapper.RoomMapper;
import airbnb.clone.model.Room;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
public class RoomManageService {

    private final RoomMapper roomMapper;
    @Autowired
    public RoomManageService(RoomMapper roomMapper) {
        this.roomMapper = roomMapper;
    }

    public void registerRoom(Room room, int userId) {
        roomMapper.register(room, userId);
    }

    public void deleteRoom(Integer id) {
        roomMapper.deleteById(id);
    }

    public void updateRoom(Integer id, Room room) {
        Room findRoom = roomMapper.findById(id);
        findRoom.setRoomName(room.getRoomName());
        findRoom.setLocation(room.getLocation());
        findRoom.setUpdatedAt(LocalDateTime.now());
    }

}
