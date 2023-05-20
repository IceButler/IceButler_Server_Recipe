package smile.iceBulterrecipe.admin.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import smile.iceBulterrecipe.admin.dto.AdminReq;
import smile.iceBulterrecipe.admin.dto.GetRecipeReportRes;
import smile.iceBulterrecipe.admin.service.AdminServiceImpl;
import smile.iceBulterrecipe.global.response.ResponseCustom;

import org.springframework.data.domain.Pageable;
@RequestMapping("/admin")
@RestController
@RequiredArgsConstructor
public class AdminController {

    private final AdminServiceImpl adminService;


    @PostMapping
    public ResponseCustom<Void> addAdmin(@RequestBody AdminReq request)
    {
        adminService.addAdmin(request);
        return ResponseCustom.OK();
    }

    //레시피 신고 내역 조회
    @GetMapping("/check")
    public ResponseCustom<Page<GetRecipeReportRes>> getRecipeReport(Pageable pageable) {
        return ResponseCustom.OK(this.adminService.getRecipeReport( pageable));
    }

    //레시피 신고완료
    @PostMapping("/{recipeReportIdx}/report")
    public ResponseCustom<?> adminReportRecipe(@PathVariable Long recipeReportIdx) {
        this.adminService.adminReportRecipe(recipeReportIdx);
        return ResponseCustom.OK();
    }
}
