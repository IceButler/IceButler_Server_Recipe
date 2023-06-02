package smile.iceBulterrecipe.admin.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import smile.iceBulterrecipe.admin.dto.request.AdminReq;
import smile.iceBulterrecipe.admin.dto.request.ReportMemoModifyReq;
import smile.iceBulterrecipe.admin.dto.response.GetRecipeReportDetailsRes;
import smile.iceBulterrecipe.admin.dto.response.GetRecipeReportRes;
import smile.iceBulterrecipe.admin.dto.assembler.AdminAssembler;
import smile.iceBulterrecipe.admin.dto.response.UserResponse;
import smile.iceBulterrecipe.admin.entity.Admin;
import smile.iceBulterrecipe.admin.exception.NotExistMemoException;
import smile.iceBulterrecipe.admin.exception.RecipeReportNotFoundException;
import smile.iceBulterrecipe.recipe.entity.Cookery;
import smile.iceBulterrecipe.recipe.entity.RecipeFood;
import smile.iceBulterrecipe.admin.repository.AdminRepository;
import smile.iceBulterrecipe.recipe.entity.Recipe;
import smile.iceBulterrecipe.recipe.entity.RecipeReport;
import smile.iceBulterrecipe.recipe.exception.RecipeNotFoundException;
import smile.iceBulterrecipe.recipe.repository.recipe.RecipeRepository;
import smile.iceBulterrecipe.recipe.repository.CookeryRepository;
import smile.iceBulterrecipe.recipe.repository.RecipeReportRepository;
import smile.iceBulterrecipe.recipe.repository.recipeFood.RecipeFoodRepository;
import smile.iceBulterrecipe.user.entity.User;
import smile.iceBulterrecipe.user.exception.UserNickNameNotFoundException;
import smile.iceBulterrecipe.user.exception.UserNotFoundException;
import smile.iceBulterrecipe.user.repository.UserRepository;


import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class AdminServiceImpl implements AdminService{
    private final AdminAssembler adminAssembler;
    private final RecipeReportRepository recipeReportRepository;
    private final RecipeFoodRepository recipeFoodRepository;
    private final CookeryRepository cookeryRepository;
    private final UserRepository userRepository;

    private final AdminRepository adminRepository;
    private final RecipeRepository recipeRepository;


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
    public Page<GetRecipeReportRes> getRecipeReport(Pageable pageable,int type) {
        Page<RecipeReport> recipeReports;
        if (type == 0) {
            recipeReports = this.recipeReportRepository.findAllByIsEnableFalse(pageable);
        } else if (type == 1) {
            recipeReports = this.recipeReportRepository.findAllByIsEnableTrue(pageable);
        }else {
            throw new RecipeReportNotFoundException();
        }
        return this.adminAssembler.toAdminReportEntity(recipeReports);
    }

    //신고 상세내역 조회
    @Override
    @Transactional
    public void removeRecipe(Long recipeReportIdx) {
        RecipeReport report = recipeReportRepository.findByRecipeReportIdxAndIsEnable(recipeReportIdx, true).orElseThrow(RecipeReportNotFoundException::new);
        Recipe recipe = recipeRepository.findByRecipeIdxAndIsEnable(report.getRecipe().getRecipeIdx(), true).orElseThrow(RecipeNotFoundException::new);
        recipe.removeRecipe();
    }

    @Override
    @Transactional
    public GetRecipeReportDetailsRes getRecipeDetails(Long recipeReportIdx) {
        RecipeReport recipeReport=this.recipeReportRepository.findByRecipeReportIdx(recipeReportIdx).orElseThrow(RecipeReportNotFoundException::new);

        List<RecipeFood> recipeFoods = this.recipeFoodRepository.findByRecipeAndIsEnable(recipeReport.getRecipe(),true);
        List<Cookery> cookery = this.cookeryRepository.findByRecipeAndIsEnableOrderByNextIdx(recipeReport.getRecipe(),true);

        return GetRecipeReportDetailsRes.toDto(recipeReport,recipeFoods,cookery);

    }
    //신고 메모 수정
    @Override
    @Transactional
    public void modifyRecipeReport(Long recipeReportIdx, ReportMemoModifyReq reportMemoModifyReq) {
        RecipeReport recipeReport=this.recipeReportRepository.findByRecipeReportIdx(recipeReportIdx).orElseThrow(RecipeReportNotFoundException::new);
        if (reportMemoModifyReq.getMemo() != null) {
            recipeReport.toUpdateReportMemo(reportMemoModifyReq.getMemo());
        } else {
            throw new NotExistMemoException();
        }

    }

    //회원별 레시피 신고내역 조회
    @Override
    public Page<GetRecipeReportRes> getUserReportCheck(String nickname,Pageable pageable ,int type) {
        List<User> users = this.userRepository.findAllByNicknameContains(nickname);
        if (users.isEmpty()) {
            throw new UserNickNameNotFoundException();
        }
        List<RecipeReport> allRecipeReports = new ArrayList<>();
        for (User user : users) {
            Page<RecipeReport> recipeReports;
            if (type == 0) {
                recipeReports = this.recipeReportRepository.findByRecipe_UserAndIsEnableFalse(user, pageable);
            } else if (type == 1) {
                recipeReports = this.recipeReportRepository.findByRecipe_UserAndIsEnableTrue(user, pageable);
            } else {
                throw new RecipeReportNotFoundException();
            }
            allRecipeReports.addAll(recipeReports.getContent());
        }
        return this.adminAssembler.toAdminReportEntity(new PageImpl<>(allRecipeReports, pageable, allRecipeReports.size()));
    }

    @Override
    public Page<GetRecipeReportRes> getUsersReportCheck(String nickname, Pageable pageable) {
        List<User> users = this.userRepository.findAllByNicknameContains(nickname);
        if (users.isEmpty()) {
            throw new UserNickNameNotFoundException();
        }
        List<RecipeReport> allRecipeReports = new ArrayList<>();
        for (User user : users) {
            Page<RecipeReport> recipeReports = this.recipeReportRepository.findByRecipe_User(user, pageable);
            allRecipeReports.addAll(recipeReports.getContent());
        }
        return this.adminAssembler.toAdminReportEntity(new PageImpl<>(allRecipeReports, pageable, allRecipeReports.size()));
    }

    @Override
    public Page<UserResponse> search(Pageable pageable, String nickname, boolean active) {
        return adminRepository.findAllByNicknameAndActive(pageable, nickname, active);
    }

    @Transactional
    @Override
    public void withdraw(Long userIdx) {
        User user = userRepository.findById(userIdx).orElseThrow(UserNotFoundException::new);
        userRepository.delete(user);
    }
    @Override
    public Page<GetRecipeReportRes> getAllRecipeReport(Pageable pageable) {
        Page<RecipeReport> recipeReports = this.recipeReportRepository.findAll(pageable);
        return this.adminAssembler.toAdminReportEntity(recipeReports);
    }

}
