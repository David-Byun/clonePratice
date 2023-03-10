package airbnb.clone.controller;

import airbnb.clone.exception.CustomException;
import airbnb.clone.exception.ErrorCode;
import airbnb.clone.model.User;
import airbnb.clone.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@Slf4j
@ResponseBody
@RequestMapping("/api")
@Validated
public class UserController {

    private final UserService manageService;

    @Autowired
    public UserController(UserService manageService) {
        this.manageService = manageService;
    }


    @PostMapping("/user")
    public ResponseEntity<HttpStatus> signUp(@Valid @RequestBody User user, BindingResult result) {
        manageService.signUp(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/user/{userId}")
    public Optional<User> findUserInfo(@PathVariable("userId") Integer userId) {
       return Optional.ofNullable(manageService.findUserInfo(userId).orElseThrow(() -> new CustomException(ErrorCode.NO_USER)));
    }

    @GetMapping("/user/all")
    public ResponseEntity<List<User>> findUsers() {
        List<User> allUser = manageService.findAllUser();
        return ResponseEntity.ok().body(allUser);
    }

    @PostMapping("/user/{userId}/delete")
    public void deleteUser(@PathVariable Integer userId) {
        manageService.delete(userId);
    }

    @PostMapping("/user/{userId}/update")
    public ResponseEntity<String> updateUser(@PathVariable int userId, @RequestBody User user) {
        manageService.update(userId, user);
        return ResponseEntity.ok().body("정상적으로 변경되었습니다");
    }

}
