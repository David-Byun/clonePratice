package airbnb.clone.service;

import airbnb.clone.mapper.RoomMapper;
import airbnb.clone.mapper.UserMapper;
import airbnb.clone.model.Room;
import airbnb.clone.model.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Slf4j
@Service
public class UserManageService {
    private final UserMapper userMapper;
    private final RoomMapper roomMapper;

    @Autowired
    public UserManageService(UserMapper userMapper, RoomMapper roomMapper) {
        this.userMapper = userMapper;
        this.roomMapper = roomMapper;
    }

    public void signUp(User user) {
        userMapper.save(user);
    }

    public User findUserInfo(int userId) {
        return userMapper.findById(userId);
    }

    public void delete(int userId) {
        userMapper.deleteById(userId);
    }

    public List<User> findAllUser() {
        return userMapper.findAll();
    }

    public void registerRoom(int userId, Room room) {
        User foundUser = userMapper.findById(userId);
        foundUser.addRooms(room);
    }

    public List<Room> getRegisteredRoom(int userId) {
        User foundUser = userMapper.findById(userId);
        return foundUser.getRooms();
    }


}
