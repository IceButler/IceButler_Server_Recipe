package smile.iceBulterrecipe.admin.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import smile.iceBulterrecipe.admin.service.AdminService;
import smile.iceBulterrecipe.global.feign.dto.request.AdminReq;
import smile.iceBulterrecipe.global.response.ResponseCustom;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/admin")
@RestController
public class AdminController {

    private final AdminService adminService;

    @PostMapping
    public ResponseCustom<Void> addAdmin(@RequestBody AdminReq request)
    {
        adminService.addAdmin(request);
        return ResponseCustom.OK();
    }
}
