package smile.iceBulterrecipe.admin.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import smile.iceBulterrecipe.admin.dto.request.AdminReq;
import smile.iceBulterrecipe.admin.dto.request.ReportMemoModifyReq;
import smile.iceBulterrecipe.admin.dto.response.*;
import smile.iceBulterrecipe.global.resolver.IsAdminLogin;

public interface AdminService {
    void addAdmin(AdminReq request,Long adminIdx);

//    Page<PostReportRes> adminReportRecipe(Long recipeIdx, Long userIdx, String reason, Pageable pageable);
    void adminReportRecipe(Long recipeReportIdx,Long adminIdx);

    Page<GetRecipeReportRes> getRecipeReport(Pageable pageable,int type,Long adminIdx);


    void removeRecipe(Long recipeReportIdx,Long adminIdx);


    GetRecipeReportDetailsRes getRecipeDetails(Long recipeReportIdx,Long adminIdx);

    void modifyRecipeReport(Long recipeReportIdx, ReportMemoModifyReq reportMemoModifyReq,Long adminIdx);

    Page<GetRecipeReportRes> getUserReportCheck(String nickname,Pageable pageable,int type,Long adminIdx);

    Page<UserResponse> search(Pageable pageable, String nickname, boolean active, boolean order,Long adminIdx);

    void withdraw(Long userIdx,Long adminIdx);

    Page<GetRecipeReportRes> getAllRecipeReport(Pageable pageable,Long adminIdx);


    Page<GetRecipeReportRes> getUsersReportCheck(String nickname, Pageable pageable,Long adminIdx);

    UserRecipeReportsRes GetUserRecipeReports(Long userIdx,Long adminIdx);
}

