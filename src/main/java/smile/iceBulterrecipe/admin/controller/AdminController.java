package smile.iceBulterrecipe.admin.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import smile.iceBulterrecipe.admin.dto.request.AdminReq;
import smile.iceBulterrecipe.admin.dto.request.ReportMemoModifyReq;
import smile.iceBulterrecipe.admin.dto.response.GetRecipeReportDetailsRes;
import smile.iceBulterrecipe.admin.dto.response.GetRecipeReportRes;
import smile.iceBulterrecipe.admin.service.AdminServiceImpl;
import smile.iceBulterrecipe.global.resolver.Admin;
import smile.iceBulterrecipe.global.resolver.AdminLoginStatus;
import smile.iceBulterrecipe.global.resolver.IsAdminLogin;
import smile.iceBulterrecipe.global.response.ResponseCustom;

import org.springframework.data.domain.Pageable;

@RequestMapping("/admin")
@RestController
@RequiredArgsConstructor
public class AdminController {

    private final AdminServiceImpl adminService;

    @Admin
    @PostMapping
    public ResponseCustom<Void> addAdmin(@RequestBody AdminReq request,
               @IsAdminLogin AdminLoginStatus loginStatus
                                         )
    {
        adminService.addAdmin(request);
        return ResponseCustom.OK();
    }

    //레시피 신고 내역 조회
    @Admin
    @GetMapping("/check")
    public ResponseCustom<Page<GetRecipeReportRes>> getRecipeReport(Pageable pageable,
                @IsAdminLogin AdminLoginStatus loginStatus

                                                                    ) {
        return ResponseCustom.OK(this.adminService.getRecipeReport( pageable));
    }

    //레시피 신고완료
    @Admin
    @PostMapping("/{recipeReportIdx}/report")
    public ResponseCustom<Void> adminReportRecipe(@PathVariable Long recipeReportIdx,
                                                  @IsAdminLogin AdminLoginStatus loginStatus

                                                  ) {
        this.adminService.adminReportRecipe(recipeReportIdx);
        return ResponseCustom.OK();
    }


    @Admin
    @PostMapping("/remove/{recipeReportIdx}/recipe")
    public ResponseCustom<Void> removeRecipe(@PathVariable Long recipeReportIdx,
                                             @IsAdminLogin AdminLoginStatus loginStatus
    ) {
        this.adminService.removeRecipe(recipeReportIdx);
        return ResponseCustom.OK();
    }

    //레시피 신고 내역 상세조회
    @Admin
    @GetMapping("/{recipeReportIdx}/detail")
    public ResponseCustom<GetRecipeReportDetailsRes> getRecipeReportDetails(@PathVariable Long recipeReportIdx,
                                                                            @IsAdminLogin AdminLoginStatus loginStatus
    ) {
        return ResponseCustom.OK(this.adminService.getRecipeDetails(recipeReportIdx));
    }

    //레시피 신고 메모 수정
    @Admin
    @PatchMapping("/{recipeReportIdx}")
    public ResponseCustom<Void> modifyRecipeReport(@PathVariable Long recipeReportIdx,
                                                   @RequestBody ReportMemoModifyReq reportMemoModifyReq,
                                                   @IsAdminLogin AdminLoginStatus loginStatus
    ) {
        this.adminService.modifyRecipeReport(recipeReportIdx, reportMemoModifyReq);
        return ResponseCustom.OK();
    }

    //회원별 레시피 신고내역 조회
    @Admin
    @GetMapping("/{nickname}/user")
    public ResponseCustom<?> getUserReportCheck(@PathVariable String nickname,
                                                              @IsAdminLogin AdminLoginStatus loginStatus
            ,Pageable pageable) {
        return ResponseCustom.OK(this.adminService.getUserReportCheck(nickname,pageable));
    }
}
