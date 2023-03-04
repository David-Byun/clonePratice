package airbnb.clone.mapper;


import airbnb.clone.model.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {

    void save(User user);

    User findById(Integer id);

    List<User> findAll();

    void deleteById(Integer id);

}
