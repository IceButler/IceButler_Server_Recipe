package smile.iceBulterrecipe.user.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import smile.iceBulterrecipe.global.response.ResponseCustom;
import smile.iceBulterrecipe.user.dto.request.AddUserReq;
import smile.iceBulterrecipe.user.service.UserServiceImpl;

@RestController
@RequestMapping(value = "/users")
@RequiredArgsConstructor
public class UserController {
    private final UserServiceImpl userService;

    @PostMapping("")
    public ResponseCustom<?> addUser(@RequestBody AddUserReq addUserReq){
        this.userService.addUser(addUserReq);
        return ResponseCustom.OK();
    }

}
