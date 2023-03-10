package airbnb.clone.mapper;

import airbnb.clone.model.Room;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@Slf4j
class RoomMapperTest {

    @Mock
    private RoomMapper roomMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @DisplayName("Room 매퍼 테스트")
    @Test
    public void mapper_테스트_Mockito() {
        Room room1 = Room.builder()
                .roomId(1)
                .roomName("hello1")
                .roomPhoto("confirm1")
                .ownerId(1)
                .build();

        Room room2 = Room.builder()
                .roomId(2)
                .roomName("hello2")
                .roomPhoto("confirm2")
                .ownerId(2)
                .build();

        Room room3 = Room.builder()
                .roomId(3)
                .roomName("hello3")
                .roomPhoto("confirm3")
                .ownerId(3)
                .build();

        List<Room> roomList = new ArrayList<>();
        roomList.add(room1);
        roomList.add(room2);
        roomList.add(room3);

        //given(findById)
        given(roomMapper.findById(1)).willReturn(Optional.ofNullable(room1));

        //when(findById)
        Optional<Room> foundRoom = roomMapper.findById(1);

        //then(findById)
        then(roomMapper).should().findById(1);
        assertThat(foundRoom.get().getRoomId()).isEqualTo(1);
        assertThat(foundRoom.get().getRoomName()).isEqualTo("hello");
        assertThat(foundRoom.get().getOwnerId()).isEqualTo(1);

        //given(findAllRooms)
        given(roomMapper.findAllRooms()).willReturn(roomList);

        //then(findAllRooms)
        List<Room> allRooms = roomMapper.findAllRooms();

        //when(findAllRooms)
        then(roomMapper).should().findAllRooms();
        assertThat(allRooms.size()).isEqualTo(3);
        assertThat(allRooms.get(0)).isEqualTo(room1);
    }
}