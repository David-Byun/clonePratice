package airbnb.clone.mapper;

import airbnb.clone.model.Room;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class RoomMapperDBTest {

    @Autowired
    private RoomMapper roomMapper;

    @Test
    @DisplayName("Room Mapper Test")
    public void mybatis_Mapper_XML_테스트() throws Exception {

        Room room = Room.builder()
                .roomId(1)
                .roomName("haha")
                .roomPhoto("dddd")
                .location("aaa")
                .build();

        roomMapper.register(room, 1);

        //given
        int num = 1;

        //when
        Optional<Room> byId = roomMapper.findById(1);

        //then
        assertThat(byId.get().getRoomId()).isEqualTo(1);
    }



}