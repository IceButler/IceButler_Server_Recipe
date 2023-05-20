package smile.iceBulterrecipe.admin.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import smile.iceBulterrecipe.admin.dto.AdminReq;
import smile.iceBulterrecipe.admin.dto.PostReportRes;

public interface AdminService {
    void addAdmin(AdminReq request);

//    Page<PostReportRes> adminReportRecipe(Long recipeIdx, Long userIdx, String reason, Pageable pageable);
    void adminReportRecipe(Long recipeReportIdx);

    Page<PostReportRes> getRecipeReport(Pageable pageable);
}
