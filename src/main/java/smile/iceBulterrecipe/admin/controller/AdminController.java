package smile.iceBulterrecipe.admin.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import smile.iceBulterrecipe.admin.dto.AdminReq;
import smile.iceBulterrecipe.admin.dto.PostReportRes;
import smile.iceBulterrecipe.admin.service.AdminServiceImpl;
import smile.iceBulterrecipe.global.resolver.Auth;
import smile.iceBulterrecipe.global.resolver.IsLogin;
import smile.iceBulterrecipe.global.resolver.LoginStatus;
import smile.iceBulterrecipe.global.response.ResponseCustom;
import smile.iceBulterrecipe.recipe.dto.request.PostRecipeReportReq;
import smile.iceBulterrecipe.recipe.service.RecipeServiceImpl;

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


//    @Auth
//    @PostMapping("/{recipeIdx}/report")
//    public ResponseCustom<Page<PostReportRes>> adminReportRecipe(@PathVariable Long recipeIdx,
//                                                                 @IsLogin LoginStatus loginStatus,
//                                                                 @RequestBody PostRecipeReportReq reportReq,
//                                                                 Pageable pageable) {
//     return ResponseCustom.OK(this.adminService.adminReportRecipe(recipeIdx, loginStatus.getUserIdx(), reportReq.getReason(),pageable));
//    }

    //레시피 신고완료
    @PostMapping("/{recipeReportIdx}/report")
    public ResponseCustom<?> adminReportRecipe(@PathVariable Long recipeReportIdx) {
        this.adminService.adminReportRecipe(recipeReportIdx);
        return ResponseCustom.OK();
    }
}
