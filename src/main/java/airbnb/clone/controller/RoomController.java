package airbnb.clone.controller;

import airbnb.clone.model.Room;
import airbnb.clone.service.RoomManageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Slf4j
@ResponseBody
@Controller
@RequestMapping("/api")
public class RoomController {
    private final RoomManageService roomManageService;

    @Autowired
    public RoomController(RoomManageService roomManageService) {
        this.roomManageService = roomManageService;
    }

    @PostMapping("/room/user/{userId}")
    public void createRoom(@RequestBody Room room, @PathVariable Integer userId) {
        roomManageService.registerRoom(room, userId);
    }
}
