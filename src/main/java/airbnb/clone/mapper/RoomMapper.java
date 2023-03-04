package airbnb.clone.mapper;

import airbnb.clone.model.Room;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RoomMapper {
    void register(Room room);
    void deleteById(Integer roomId);
    void update(Room room);
    Room findById(Integer roomId);
}
