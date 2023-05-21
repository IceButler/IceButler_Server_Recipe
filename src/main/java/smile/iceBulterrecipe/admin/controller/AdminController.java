package smile.iceBulterrecipe.admin.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import smile.iceBulterrecipe.admin.dto.request.AdminReq;
import smile.iceBulterrecipe.admin.dto.request.ReportMemoModifyReq;
import smile.iceBulterrecipe.admin.dto.response.GetRecipeReportDetailsRes;
import smile.iceBulterrecipe.admin.dto.response.GetRecipeReportRes;
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
    public ResponseCustom<Void> adminReportRecipe(@PathVariable Long recipeReportIdx) {
        this.adminService.adminReportRecipe(recipeReportIdx);
        return ResponseCustom.OK();
    }

    //레시피 신고 내역 상세조회
    @GetMapping("/{recipeReportIdx}/detail")
    public ResponseCustom<GetRecipeReportDetailsRes> getRecipeReportDetails(@PathVariable Long recipeReportIdx) {
        return ResponseCustom.OK(this.adminService.getRecipeDetails(recipeReportIdx));
    }

    //레시피 신고 메모 수정
    @PatchMapping("/{recipeReportIdx}")
    public ResponseCustom<Void> modifyRecipeReport(@PathVariable Long recipeReportIdx,
                                          @RequestBody ReportMemoModifyReq reportMemoModifyReq
                                          ) {
        this.adminService.modifyRecipeReport(recipeReportIdx, reportMemoModifyReq);
        return ResponseCustom.OK();
    }

    //회원별 레시피 신고내역 조회
    @GetMapping("/{nickname}/user")
    public ResponseCustom<?> getUserReportCheck(@PathVariable String nickname,Pageable pageable) {
        return ResponseCustom.OK(this.adminService.getUserReportCheck(nickname,pageable));
    }



}
