package airbnb.clone.service;

import airbnb.clone.mapper.UserMapper;
import airbnb.clone.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


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

    public Optional<User> findUserInfo(Integer userId) {
        return userMapper.findById(userId);
    }

    public void delete(Integer userId) {
        userMapper.deleteById(userId);
    }

    public List<User> findAllUser() {
        return userMapper.findAll();
    }

}
