package airbnb.clone.controller;

import airbnb.clone.exception.ErrorCode;
import airbnb.clone.exception.ErrorResponse;
import airbnb.clone.model.User;
import airbnb.clone.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@ResponseBody
@RequestMapping("/api")
public class UserController {

    private final UserService manageService;

    @Autowired
    public UserController(UserService manageService) {
        this.manageService = manageService;
    }

    @PostMapping("/user")
    public void signUp(User user) {
        manageService.signUp(user);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<User> findUserInfo(@PathVariable("userId") Integer userId) {
        User foundUser = manageService.findUserInfo(userId);
        if (foundUser == null) {
            ErrorResponse.of(ErrorCode.NO_USER);
        }
        return ResponseEntity.ok().body(foundUser);
    }

    @GetMapping("")
    public ResponseEntity<List<User>> findUsers() {
        List<User> allUser = manageService.findAllUser();
        return ResponseEntity.ok().body(allUser);
    }

}
