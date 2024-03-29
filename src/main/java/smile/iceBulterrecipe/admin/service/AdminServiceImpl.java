package smile.iceBulterrecipe.admin.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import smile.iceBulterrecipe.admin.dto.request.AdminReq;
import smile.iceBulterrecipe.admin.dto.request.ReportMemoModifyReq;
import smile.iceBulterrecipe.admin.dto.response.*;
import smile.iceBulterrecipe.admin.dto.assembler.AdminAssembler;
import smile.iceBulterrecipe.admin.entity.Admin;
import smile.iceBulterrecipe.admin.exception.NotExistMemoException;
import smile.iceBulterrecipe.admin.exception.RecipeReportNotFoundException;
import smile.iceBulterrecipe.global.resolver.IsAdminLogin;
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
import smile.iceBulterrecipe.user.exception.AdminNotFoundException;
import smile.iceBulterrecipe.user.exception.UserNickNameNotFoundException;
import smile.iceBulterrecipe.user.exception.UserNotFoundException;
import smile.iceBulterrecipe.user.repository.UserRepository;


import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
    public void addAdmin(AdminReq request,Long adminIdx) {
        adminRepository.findByAdminIdxAndIsEnable(adminIdx,true).orElseThrow(AdminNotFoundException::new);
        Admin admin = new Admin(request.getAdminIdx(), request.getEmail());
        adminRepository.save(admin);
    }

    //신고 완료 처리
    @Override
    @Transactional
    public void adminReportRecipe(Long recipeReportIdx,Long adminIdx) {
        adminRepository.findByAdminIdxAndIsEnable(adminIdx,true).orElseThrow(AdminNotFoundException::new);
        RecipeReport recipeReport=recipeReportRepository.findByRecipeReportIdxAndIsEnable(recipeReportIdx,true).orElseThrow(RecipeReportNotFoundException::new);
        recipeReport.adminReportRecipe();
    }

    //레시피 신고 내역 조회
    @Override
    public Page<GetRecipeReportRes> getRecipeReport(Pageable pageable,int type,Long adminIdx) {
        adminRepository.findByAdminIdxAndIsEnable(adminIdx,true).orElseThrow(AdminNotFoundException::new);
        Page<RecipeReport> recipeReports;

        if (type == 0) {
            recipeReports = this.recipeReportRepository.findAllByIsEnableFalseOrderByCreatedAtDesc(pageable);
        } else if (type == 1) {
            recipeReports = this.recipeReportRepository.findAllByIsEnableTrueOrderByCreatedAtDesc(pageable);
        }else {
            throw new RecipeReportNotFoundException();
        }
        return this.adminAssembler.toAdminReportEntity(recipeReports);
    }

    //신고 상세내역 조회
    @Override
    @Transactional
    public void removeRecipe(Long recipeIdx,Long adminIdx) {
        adminRepository.findByAdminIdxAndIsEnable(adminIdx,true).orElseThrow(AdminNotFoundException::new);
        Recipe recipe = recipeRepository.findByRecipeIdxAndIsEnable(recipeIdx, true).orElseThrow(RecipeNotFoundException::new);
        recipeRepository.delete(recipe);
    }

    @Override
    @Transactional
    public GetRecipeReportDetailsRes getRecipeDetails(Long recipeReportIdx,Long adminIdx) {
        adminRepository.findByAdminIdxAndIsEnable(adminIdx,true).orElseThrow(AdminNotFoundException::new);
        RecipeReport recipeReport=this.recipeReportRepository.findByRecipeReportIdx(recipeReportIdx).orElseThrow(RecipeReportNotFoundException::new);

        List<RecipeFood> recipeFoods = this.recipeFoodRepository.findByRecipeAndIsEnable(recipeReport.getRecipe(),true);
        List<Cookery> cookery = this.cookeryRepository.findByRecipeAndIsEnableOrderByNextIdx(recipeReport.getRecipe(),true);

        return GetRecipeReportDetailsRes.toDto(recipeReport,recipeFoods,cookery);

    }

    @Override
    public UserRecipeReportsRes GetUserRecipeReports(Long userIdx,Long adminIdx) {
        adminRepository.findByAdminIdxAndIsEnable(adminIdx,true).orElseThrow(AdminNotFoundException::new);
        List<RecipeReport> recipeReports = recipeReportRepository.findByRecipe_UserUserIdxOrderByCreatedAtDesc(userIdx);
        return UserRecipeReportsRes.toDto(userIdx, recipeReports);


    }

    //신고 메모 수정
    @Override
    @Transactional
    public void modifyRecipeReport(Long recipeReportIdx, ReportMemoModifyReq reportMemoModifyReq,Long adminIdx) {
        adminRepository.findByAdminIdxAndIsEnable(adminIdx,true).orElseThrow(AdminNotFoundException::new);
        RecipeReport recipeReport=this.recipeReportRepository.findByRecipeReportIdx(recipeReportIdx).orElseThrow(RecipeReportNotFoundException::new);
        if (reportMemoModifyReq.getMemo() != null) {
            recipeReport.toUpdateReportMemo(reportMemoModifyReq.getMemo());
        } else {
            throw new NotExistMemoException();
        }

    }

    //회원별 레시피 신고내역 조회
    @Override
    public Page<GetRecipeReportRes> getUserReportCheck(String nickname,Pageable pageable ,int type,Long adminIdx) {
        adminRepository.findByAdminIdxAndIsEnable(adminIdx,true).orElseThrow(AdminNotFoundException::new);
        List<User> users = this.userRepository.findAllByNicknameContains(nickname);
        if (users.isEmpty()) {
            throw new UserNickNameNotFoundException();
        }
        List<RecipeReport> allRecipeReports = new ArrayList<>();
        for (User user : users) {
            Page<RecipeReport> recipeReports;
            if (type == 0) {
                recipeReports = this.recipeReportRepository.findByRecipe_UserAndIsEnableFalseOrderByCreatedAtDesc(user, pageable);
            } else if (type == 1) {
                recipeReports = this.recipeReportRepository.findByRecipe_UserAndIsEnableTrueOrderByCreatedAtDesc(user, pageable);
            } else {
                throw new RecipeReportNotFoundException();
            }
            allRecipeReports.addAll(recipeReports.getContent());
        }
        return this.adminAssembler.toAdminReportEntity(new PageImpl<>(allRecipeReports, pageable, allRecipeReports.size()));
    }

    @Override
    public Page<GetRecipeReportRes> getUsersReportCheck(String nickname, Pageable pageable,Long adminIdx) {
        adminRepository.findByAdminIdxAndIsEnable(adminIdx,true).orElseThrow(AdminNotFoundException::new);
        List<User> users = this.userRepository.findAllByNicknameContains(nickname);
        if (users.isEmpty()) {
            throw new UserNickNameNotFoundException();
        }
        List<RecipeReport> allRecipeReports = new ArrayList<>();
        for (User user : users) {
            Page<RecipeReport> recipeReports = this.recipeReportRepository.findByRecipe_UserOrderByCreatedAtDesc(user, pageable);
            allRecipeReports.addAll(recipeReports.getContent());
        }
        return this.adminAssembler.toAdminReportEntity(new PageImpl<>(allRecipeReports, pageable, allRecipeReports.size()));
    }



    @Override
    public Page<UserResponse> search(Pageable pageable, String nickname, boolean active, boolean order,Long adminIdx) {
        adminRepository.findByAdminIdxAndIsEnable(adminIdx,true).orElseThrow(AdminNotFoundException::new);
        return adminRepository.findAllByNicknameAndActive(pageable, nickname, active, order);
    }

    @Transactional
    @Override
    public void withdraw(Long userIdx,Long adminIdx) {
        adminRepository.findByAdminIdxAndIsEnable(adminIdx,true).orElseThrow(AdminNotFoundException::new);
        User user = userRepository.findById(userIdx).orElseThrow(UserNotFoundException::new);
        userRepository.delete(user);
    }
    @Override
    public Page<GetRecipeReportRes> getAllRecipeReport(Pageable pageable,Long adminIdx) {
        adminRepository.findByAdminIdxAndIsEnable(adminIdx,true).orElseThrow(AdminNotFoundException::new);
        Sort sort = Sort.by(Sort.Direction.DESC, "createdAt");
        pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort);

        Page<RecipeReport> recipeReports = this.recipeReportRepository.findAll(pageable);
        return this.adminAssembler.toAdminReportEntity(recipeReports);
    }

}
