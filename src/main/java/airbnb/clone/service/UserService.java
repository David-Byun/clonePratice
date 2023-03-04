package airbnb.clone.service;

import airbnb.clone.mapper.RoomMapper;
import airbnb.clone.mapper.UserMapper;
import airbnb.clone.model.Room;
import airbnb.clone.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Slf4j
@Service
public class UserService {
    private final UserMapper userMapper;

    @Autowired
    public UserService(UserMapper userMapper) {
        this.userMapper = userMapper;
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

}
