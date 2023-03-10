package airbnb.clone.service;

import airbnb.clone.exception.CustomException;
import airbnb.clone.exception.ErrorCode;
import airbnb.clone.mapper.RoomMapper;
import airbnb.clone.model.Room;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@Slf4j
class RoomManageServiceTest {

    @Mock
    private RoomMapper roomMapper;

    @Spy
    @InjectMocks
    private RoomManageService roomManageService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Room 서비스 테스트")
    public void service_테스트_Mockito() throws Exception {

        Room room1 = Room.builder()
                .roomId(1)
                .roomName("hello1")
                .roomPhoto("confirm1")
                .build();

        Room room2 = Room.builder()
                .roomId(2)
                .roomName("hello2")
                .roomPhoto("confirm2")
                .build();

        Room room3 = Room.builder()
                .roomId(3)
                .roomName("hello3")
                .roomPhoto("confirm3")
                .build();


        List<Room> roomList = new ArrayList<>();
        roomList.add(room1);
        roomList.add(room2);
        roomList.add(room3);


        roomMapper.register(room1, 1);
        roomMapper.register(room2, 2);
        roomMapper.register(room3, 3);

        //given
        given(roomMapper.findById(1)).willReturn(Optional.of(room2));
        given(roomMapper.findAllRooms()).willReturn(roomList);
        log.info("전체 방 리스트 {}", roomMapper.findAllRooms());

        //when
        Optional<Room> roomById = roomManageService.findRoomById(1);
        log.info("1번 방 확인 {}", roomById);

        roomManageService.updateRoom(1, room2);
        log.info("2번룸 {}", roomManageService.findRoomById(2));
        roomManageService.deleteRoom(2);

        List<Room> allRooms = roomManageService.findAllRooms();

        then(roomManageService).should().updateRoom(1, room2);
        then(roomManageService).should().deleteRoom(2);


        then(roomManageService).should().findRoomById(1);
        then(roomMapper).should(times(2)).findById(1);

        assertThat(roomById.get().getRoomId()).isEqualTo(2);
        assertThat(roomById.get().getRoomName()).isEqualTo("hello2");

        then(roomManageService).should().findAllRooms();
        then(roomMapper).should().findAllRooms();
        assertThat(allRooms.size()).isEqualTo(3);
        assertThat(allRooms.get(0).getRoomName()).isEqualTo("hello2");

        assertThatThrownBy(() -> roomManageService.findRoomById(2)).isInstanceOf(CustomException.class);


        assertThat(roomManageService.findRoomById(3).get().getRoomName()).isEqualTo("hello1");

        //log
        log.info(roomById.toString());
        log.info(allRooms.toString());
    }



}