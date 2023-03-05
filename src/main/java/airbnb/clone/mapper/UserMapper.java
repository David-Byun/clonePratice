package airbnb.clone.mapper;


import airbnb.clone.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface UserMapper {

    void save(User user);

    Optional<User> findById(int id);

    List<User> findAll();

    void deleteById(int id);

    void update(@Param("userId") int userId, @Param("user") User user);

}
