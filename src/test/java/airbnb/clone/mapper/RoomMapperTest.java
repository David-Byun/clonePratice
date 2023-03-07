package airbnb.clone.mapper;

import airbnb.clone.model.Room;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
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
        Room room = Room.builder()
                .roomId(1)
                .roomName("hello")
                .roomPhoto("confirm")
                .ownerId(1)
                .build();

        //given(findById)
        given(roomMapper.findById(1)).willReturn(Optional.ofNullable(room));

        //when(findById)
        Optional<Room> foundRoom = roomMapper.findById(1);

        //then(findById)
        then(roomMapper).should().findById(1);
        assertThat(foundRoom.get().getRoomId()).isEqualTo(1);
        assertThat(foundRoom.get().getRoomName()).isEqualTo("hello");
        assertThat(foundRoom.get().getOwnerId()).isEqualTo(1);

        //given


    // then
    //then(orderMapper).should().getOrder(1L);
    //then(orderService).should().getOrderTest(1L);

    //assertThat(vo.getSeq()).isEqualTo(1L);
    //assertThat(vo.getProductSeq()).isEqualTo(1L);
    //assertThat(vo.getProductName()).isEqualTo("test");

    // log
    // log.info(vo.toString());
    }


}