package smile.iceBulterrecipe.admin.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import smile.iceBulterrecipe.admin.dto.request.AdminReq;
import smile.iceBulterrecipe.admin.dto.request.ReportMemoModifyReq;
import smile.iceBulterrecipe.admin.dto.response.GetRecipeReportDetailsRes;
import smile.iceBulterrecipe.admin.dto.response.GetRecipeReportRes;
import smile.iceBulterrecipe.admin.dto.response.UserRecipeReportsRes;
import smile.iceBulterrecipe.admin.dto.response.UserResponse;
import smile.iceBulterrecipe.admin.service.AdminServiceImpl;
import smile.iceBulterrecipe.food.dto.assembler.FoodAssembler;
import smile.iceBulterrecipe.food.entity.Food;
import smile.iceBulterrecipe.food.entity.FoodCategory;
import smile.iceBulterrecipe.food.repository.FoodRepository;
import smile.iceBulterrecipe.global.resolver.Admin;
import smile.iceBulterrecipe.global.resolver.AdminLoginStatus;
import smile.iceBulterrecipe.global.resolver.IsAdminLogin;
import smile.iceBulterrecipe.global.response.ResponseCustom;

import org.springframework.data.domain.Pageable;
import smile.iceBulterrecipe.global.sqs.AmazonSQSSender;
import smile.iceBulterrecipe.global.sqs.FoodData;
import smile.iceBulterrecipe.recipe.dto.request.PostRecipeFoodReq;

import java.util.UUID;

@RequestMapping("/admin")
@RestController
@RequiredArgsConstructor
public class AdminController {

    private final AdminServiceImpl adminService;
    private final AmazonSQSSender amazonSQSSender;
    private final FoodRepository foodRepository;

    private final FoodAssembler foodAssembler;
    @Admin
    @PostMapping
    public ResponseCustom<Void> addAdmin(@RequestBody AdminReq request,
               @IsAdminLogin AdminLoginStatus loginStatus
                                         )
    {
        adminService.addAdmin(request);
        return ResponseCustom.OK();
    }

//    @Admin
    @GetMapping("/users")
    public ResponseCustom<Page<UserResponse>> search(
//            @IsAdminLogin AdminLoginStatus loginStatus,
            Pageable pageable,
            @RequestParam(defaultValue = "") String nickname,
            @RequestParam(defaultValue = "true") boolean active,
            @RequestParam(defaultValue = "false") boolean order
    )
    {
        return ResponseCustom.OK(adminService.search(pageable, nickname, active, order));
    }

    @DeleteMapping("/users/{userIdx}")
    public ResponseCustom<Void> withdraw(@PathVariable Long userIdx)
    {
        adminService.withdraw(userIdx);
        return ResponseCustom.OK();
    }

    //레시피 신고완료
    @Admin
    @PostMapping("/reports/{recipeReportIdx}")
    public ResponseCustom<Void> adminReportRecipe(@PathVariable Long recipeReportIdx,
                                                  @IsAdminLogin AdminLoginStatus loginStatus

                                                  ) {
        this.adminService.adminReportRecipe(recipeReportIdx);
        return ResponseCustom.OK();
    }


    @Admin
    @DeleteMapping("/recipes/{recipeIdx}")
    public ResponseCustom<Void> removeRecipe(@PathVariable Long recipeIdx,
                                             @IsAdminLogin AdminLoginStatus loginStatus
    ) {
        this.adminService.removeRecipe(recipeIdx);
        return ResponseCustom.OK();
    }

    //레시피 신고 내역 상세조회
    @Admin
    @GetMapping("/reports/{recipeReportIdx}")
    public ResponseCustom<GetRecipeReportDetailsRes> getRecipeReportDetails(@PathVariable Long recipeReportIdx,
//                                                                            @RequestParam(required = true) Integer type,
                                                                            @IsAdminLogin AdminLoginStatus loginStatus
    ) {
        return ResponseCustom.OK(this.adminService.getRecipeDetails(recipeReportIdx));
    }

    //레시피 신고 메모 수정
    @Admin
    @PatchMapping("/reports/{recipeReportIdx}")
    public ResponseCustom<Void> modifyRecipeReport(@PathVariable Long recipeReportIdx,
                                                   @RequestBody ReportMemoModifyReq reportMemoModifyReq,
                                                   @IsAdminLogin AdminLoginStatus loginStatus
    ) {
        this.adminService.modifyRecipeReport(recipeReportIdx, reportMemoModifyReq);
        return ResponseCustom.OK();
    }

    //회원 정지 안내
    @Admin
    @GetMapping("/reports/users/{userIdx}")
    public ResponseCustom<UserRecipeReportsRes> GetUserRecipeReports(@PathVariable Long userIdx,
                                                                     @IsAdminLogin AdminLoginStatus loginStatus
    ){
        return ResponseCustom.OK(this.adminService.GetUserRecipeReports(userIdx));
    }

    //레시피 신고내역 조회
    @Admin
    @GetMapping("/reports")
    public ResponseCustom<Page<GetRecipeReportRes>> getUserReportCheck(
            @RequestParam(required = false) Integer type,
            @RequestParam(required = false) String nickname,
            Pageable pageable,
            @IsAdminLogin AdminLoginStatus loginStatus
            ) {
        if (type == null) {
            if (nickname != null) {
                return ResponseCustom.OK(this.adminService.getUsersReportCheck(nickname, pageable));
            } else{
                return ResponseCustom.OK(this.adminService.getAllRecipeReport(pageable));
            }
        }else{
            if (nickname != null) {
                return ResponseCustom.OK(this.adminService.getUserReportCheck(nickname, pageable, type));
            } else {
                return ResponseCustom.OK(this.adminService.getRecipeReport(pageable, type));
            }
        }

    }


    @GetMapping("/hihitest")
    public void hihiTest() {
        PostRecipeFoodReq postRecipeFoodReq = new PostRecipeFoodReq("썩은 고기", "상세 썩은 고기");
        Food food = this.foodRepository.save(foodAssembler.toEntity(postRecipeFoodReq, "육류"));
        amazonSQSSender.sendMessage(FoodData.toDto(food));
    }


}
