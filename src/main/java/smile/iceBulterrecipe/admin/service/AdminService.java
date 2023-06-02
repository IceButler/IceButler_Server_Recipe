package smile.iceBulterrecipe.admin.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import smile.iceBulterrecipe.admin.dto.request.AdminReq;
import smile.iceBulterrecipe.admin.dto.request.ReportMemoModifyReq;
import smile.iceBulterrecipe.admin.dto.response.*;

public interface AdminService {
    void addAdmin(AdminReq request);

//    Page<PostReportRes> adminReportRecipe(Long recipeIdx, Long userIdx, String reason, Pageable pageable);
    void adminReportRecipe(Long recipeReportIdx);

    Page<GetRecipeReportRes> getRecipeReport(Pageable pageable,int type);


    void removeRecipe(Long recipeReportIdx);


    GetRecipeReportDetailsRes getRecipeDetails(Long recipeReportIdx);

    void modifyRecipeReport(Long recipeReportIdx, ReportMemoModifyReq reportMemoModifyReq);

    Page<GetRecipeReportRes> getUserReportCheck(String nickname,Pageable pageable,int type);

    Page<UserResponse> search(Pageable pageable, String nickname, boolean active, boolean order);

    void withdraw(Long userIdx);

    Page<GetRecipeReportRes> getAllRecipeReport(Pageable pageable);


    Page<GetRecipeReportRes> getUsersReportCheck(String nickname, Pageable pageable);

    UserRecipeReportsRes GetUserRecipeReports(Long userIdx);
}

