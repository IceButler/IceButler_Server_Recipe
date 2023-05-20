package smile.iceBulterrecipe.admin.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import smile.iceBulterrecipe.admin.dto.AdminReq;
import smile.iceBulterrecipe.admin.dto.GetRecipeReportRes;
import smile.iceBulterrecipe.admin.dto.assembler.AdminAssembler;
import smile.iceBulterrecipe.admin.entity.Admin;
import smile.iceBulterrecipe.admin.exception.RecipeReportNotFoundException;
import smile.iceBulterrecipe.admin.repository.AdminRepository;
import smile.iceBulterrecipe.recipe.entity.RecipeReport;
import smile.iceBulterrecipe.recipe.repository.RecipeReportRepository;

@RequiredArgsConstructor
@Service
public class AdminServiceImpl implements AdminService{
    private final AdminAssembler adminAssembler;
    private final RecipeReportRepository recipeReportRepository;
    private final AdminRepository adminRepository;


    @Transactional
    @Override
    public void addAdmin(AdminReq request) {
        Admin admin = new Admin(request.getAdminIdx(), request.getEmail());
        adminRepository.save(admin);
    }

    //신고 완료 처리
    @Override
    @Transactional
    public void adminReportRecipe(Long recipeReportIdx) {
        RecipeReport recipeReport=recipeReportRepository.findByRecipeReportIdxAndIsEnable(recipeReportIdx,true).orElseThrow(RecipeReportNotFoundException::new);
        recipeReport.adminReportRecipe();
    }

    //신고 내역 조회
    @Override
    public Page<GetRecipeReportRes> getRecipeReport(Pageable pageable) {
        Page<RecipeReport> recipeReports=this.recipeReportRepository.findAll(pageable);
        return this.adminAssembler.toAdminReportEntity(recipeReports);
    }
}
