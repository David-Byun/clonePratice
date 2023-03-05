package airbnb.clone.service;

import airbnb.clone.exception.CustomException;
import airbnb.clone.exception.ErrorCode;
import airbnb.clone.mapper.RoomMapper;
import airbnb.clone.model.Room;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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
        Optional<Room> findRoom = roomMapper.findById(id);
        if(findRoom.isPresent()) {
            findRoom.get().setRoomName(room.getRoomName());
            findRoom.get().setLocation(room.getLocation());
            findRoom.get().setUpdatedAt(LocalDateTime.now());
            roomMapper.update(id,findRoom.get());
        } else {
            throw new CustomException(ErrorCode.NO_ROOM);
        }
    }

    public Optional<Room> findRoomById(int id) {
        return Optional.of(roomMapper.findById(id).orElseThrow(() -> new CustomException(ErrorCode.NO_ROOM)));
    }

    public List<Room> findAllRooms() {
        return roomMapper.findAllRooms();
    }
}
