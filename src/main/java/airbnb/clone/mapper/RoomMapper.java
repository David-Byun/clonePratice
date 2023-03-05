package airbnb.clone.mapper;

import airbnb.clone.model.Room;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface RoomMapper {
    void register(@Param("room") Room room, @Param("ownerId") Integer ownerId);
    void deleteById(int roomId);
    void update(Room room);
    Room findById(int roomId);
}
