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
import smile.iceBulterrecipe.admin.service.AdminService;
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

import java.util.UUID;

@RequestMapping("/admin")
@RestController
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;
    private final AmazonSQSSender amazonSQSSender;
    private final FoodRepository foodRepository;

    @Admin
    @PostMapping
    public ResponseCustom<Void> addAdmin(@RequestBody AdminReq request,
               @IsAdminLogin AdminLoginStatus loginStatus
                                         )
    {
        adminService.addAdmin(request,loginStatus.getAdminIdx());
        return ResponseCustom.OK();
    }

    @Admin
    @GetMapping("/users")
    public ResponseCustom<Page<UserResponse>> search(
            @IsAdminLogin AdminLoginStatus loginStatus,
            Pageable pageable,
            @RequestParam(defaultValue = "") String nickname,
            @RequestParam(defaultValue = "true") boolean active,
            @RequestParam(defaultValue = "false") boolean order
    )
    {
        return ResponseCustom.OK(adminService.search(pageable, nickname, active, order,loginStatus.getAdminIdx()));
    }

    @Admin
    @DeleteMapping("/users/{userIdx}")
    public ResponseCustom<Void> withdraw(            @IsAdminLogin AdminLoginStatus loginStatus,
                                                     @PathVariable Long userIdx)
    {
        adminService.withdraw(userIdx, loginStatus.getAdminIdx());
        return ResponseCustom.OK();
    }

    //레시피 신고완료
    @Admin
    @PostMapping("/reports/{recipeReportIdx}")
    public ResponseCustom<Void> adminReportRecipe(@PathVariable Long recipeReportIdx,
                                                  @IsAdminLogin AdminLoginStatus loginStatus

                                                  ) {
        this.adminService.adminReportRecipe(recipeReportIdx,loginStatus.getAdminIdx());
        return ResponseCustom.OK();
    }


    @Admin
    @DeleteMapping("/recipes/{recipeIdx}")
    public ResponseCustom<Void> removeRecipe(@PathVariable Long recipeIdx,
                                             @IsAdminLogin AdminLoginStatus loginStatus
    ) {
        this.adminService.removeRecipe(recipeIdx,loginStatus.getAdminIdx());
        return ResponseCustom.OK();
    }

    //레시피 신고 내역 상세조회
    @Admin
    @GetMapping("/reports/{recipeReportIdx}")
    public ResponseCustom<GetRecipeReportDetailsRes> getRecipeReportDetails(@PathVariable Long recipeReportIdx,
//                                                                            @RequestParam(required = true) Integer type,
                                                                            @IsAdminLogin AdminLoginStatus loginStatus
    ) {
        return ResponseCustom.OK(this.adminService.getRecipeDetails(recipeReportIdx,loginStatus.getAdminIdx()));
    }

    //레시피 신고 메모 수정
    @Admin
    @PatchMapping("/reports/{recipeReportIdx}")
    public ResponseCustom<Void> modifyRecipeReport(@PathVariable Long recipeReportIdx,
                                                   @RequestBody ReportMemoModifyReq reportMemoModifyReq,
                                                   @IsAdminLogin AdminLoginStatus loginStatus
    ) {
        this.adminService.modifyRecipeReport(recipeReportIdx, reportMemoModifyReq,loginStatus.getAdminIdx());
        return ResponseCustom.OK();
    }

    //회원 정지 안내
    @Admin
    @GetMapping("/reports/users/{userIdx}")
    public ResponseCustom<UserRecipeReportsRes> GetUserRecipeReports(@PathVariable Long userIdx,
                                                                     @IsAdminLogin AdminLoginStatus loginStatus
    ){
        return ResponseCustom.OK(this.adminService.GetUserRecipeReports(userIdx,loginStatus.getAdminIdx()));
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
                return ResponseCustom.OK(this.adminService.getUsersReportCheck(nickname, pageable,loginStatus.getAdminIdx()));
            } else{
                return ResponseCustom.OK(this.adminService.getAllRecipeReport(pageable,loginStatus.getAdminIdx()));
            }
        }else{
            if (nickname != null) {
                return ResponseCustom.OK(this.adminService.getUserReportCheck(nickname, pageable, type,loginStatus.getAdminIdx()));
            } else {
                return ResponseCustom.OK(this.adminService.getRecipeReport(pageable, type,loginStatus.getAdminIdx()));
            }
        }

    }


    @GetMapping("/sqs-test")
    public ResponseCustom<Void> getUserReportCheck() {
        Food testFood = Food.builder()
                .uuid(UUID.randomUUID())
                .foodName("testFoodName2")
                .foodCategory(FoodCategory.PROCESSED_FOOD)
                .foodImgKey("food/testFoodName2.img")
                .build();

        foodRepository.save(testFood);
        amazonSQSSender.sendMessage(FoodData.toDto(testFood));

        return ResponseCustom.OK();
    }


}
