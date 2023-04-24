package smile.iceBulterrecipe.user.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import smile.iceBulterrecipe.global.response.ResponseCustom;
import smile.iceBulterrecipe.user.dto.request.UserReq;
import smile.iceBulterrecipe.user.service.UserServiceImpl;

@RestController
@RequestMapping(value = "/users")
@RequiredArgsConstructor
public class UserController {
    private final UserServiceImpl userService;

    @PostMapping("")
    public ResponseCustom<?> addUser(@RequestBody UserReq userReq){
        this.userService.addUser(userReq);
        return ResponseCustom.OK();
    }

    @PatchMapping("")
    public ResponseCustom<?> changeUserProfile(@RequestBody UserReq userReq){
        this.userService.changeUserProfile(userReq);
        return ResponseCustom.OK();
    }

    @DeleteMapping("")
    public ResponseCustom<?> deleteUser(@RequestBody UserReq userReq){
        this.userService.deleteUser(userReq);
        return ResponseCustom.OK();
    }
}
