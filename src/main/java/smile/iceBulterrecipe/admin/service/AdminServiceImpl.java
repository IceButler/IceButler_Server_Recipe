package smile.iceBulterrecipe.admin.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import smile.iceBulterrecipe.admin.dto.request.AdminReq;
import smile.iceBulterrecipe.admin.dto.request.ReportMemoModifyReq;
import smile.iceBulterrecipe.admin.dto.response.GetRecipeReportDetailsRes;
import smile.iceBulterrecipe.admin.dto.response.GetRecipeReportRes;
import smile.iceBulterrecipe.admin.dto.assembler.AdminAssembler;
import smile.iceBulterrecipe.admin.entity.Admin;
import smile.iceBulterrecipe.admin.exception.NotExistMemoException;
import smile.iceBulterrecipe.admin.exception.RecipeReportNotFoundException;
import smile.iceBulterrecipe.recipe.entity.Cookery;
import smile.iceBulterrecipe.recipe.entity.RecipeFood;
import smile.iceBulterrecipe.admin.repository.AdminRepository;
import smile.iceBulterrecipe.recipe.entity.RecipeReport;
import smile.iceBulterrecipe.recipe.repository.CookeryRepository;
import smile.iceBulterrecipe.recipe.repository.RecipeReportRepository;
import smile.iceBulterrecipe.recipe.repository.recipeFood.RecipeFoodRepository;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AdminServiceImpl implements AdminService{
    private final AdminAssembler adminAssembler;
    private final RecipeReportRepository recipeReportRepository;
    private final RecipeFoodRepository recipeFoodRepository;
    private final CookeryRepository cookeryRepository;
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

    //신고 상세내역 조회
    @Override
    public GetRecipeReportDetailsRes getRecipeDetails(Long recipeReportIdx) {
        RecipeReport recipeReport=this.recipeReportRepository.findByRecipeReportIdxAndIsEnable(recipeReportIdx,true).orElseThrow(RecipeReportNotFoundException::new);
        List<RecipeFood> recipeFoods = this.recipeFoodRepository.findByRecipeAndIsEnable(recipeReport.getRecipe(),true);
        List<Cookery> cookery = this.cookeryRepository.findByRecipeAndIsEnableOrderByNextIdx(recipeReport.getRecipe(),true);

        return GetRecipeReportDetailsRes.toDto(recipeReport,recipeFoods,cookery);

    }
    //신고 메모 수정
    @Override
    @Transactional
    public void modifyRecipeReport(Long recipeReportIdx, ReportMemoModifyReq reportMemoModifyReq) {
        RecipeReport recipeReport=this.recipeReportRepository.findByRecipeReportIdxAndIsEnable(recipeReportIdx,true).orElseThrow(RecipeReportNotFoundException::new);
        if (reportMemoModifyReq.getMemo() != null) {
            recipeReport.toUpdateReportMemo(reportMemoModifyReq.getMemo());
        } else {
            throw new NotExistMemoException();
        }
    }
}
