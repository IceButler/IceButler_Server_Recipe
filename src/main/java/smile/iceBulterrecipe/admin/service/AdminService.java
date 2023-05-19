package smile.iceBulterrecipe.admin.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import smile.iceBulterrecipe.admin.dto.PostReportRes;

public interface AdminService {
    Page<PostReportRes> adminReportRecipe(Long recipeIdx, Long userIdx, String reason, Pageable pageable);
}
