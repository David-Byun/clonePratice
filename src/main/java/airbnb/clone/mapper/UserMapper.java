package airbnb.clone.mapper;


import airbnb.clone.model.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface UserMapper {

    void save(User user);

    Optional<User> findById(Integer id);

    List<User> findAll();

    void deleteById(Integer id);

}
