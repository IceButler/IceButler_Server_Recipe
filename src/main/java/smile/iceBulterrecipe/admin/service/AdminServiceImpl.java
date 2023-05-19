package smile.iceBulterrecipe.admin.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import smile.iceBulterrecipe.admin.dto.PostReportRes;
import smile.iceBulterrecipe.recipe.Reason;
import smile.iceBulterrecipe.recipe.dto.assembler.RecipeAssembler;
import smile.iceBulterrecipe.recipe.entity.Recipe;
import smile.iceBulterrecipe.recipe.entity.RecipeReport;
import smile.iceBulterrecipe.recipe.exception.RecipeNotFoundException;
import smile.iceBulterrecipe.recipe.repository.RecipeReportRepository;
import smile.iceBulterrecipe.recipe.repository.recipe.RecipeRepository;
import smile.iceBulterrecipe.user.entity.User;
import smile.iceBulterrecipe.user.exception.UserNotFoundException;
import smile.iceBulterrecipe.user.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class AdminServiceImpl implements AdminService{
    private final UserRepository userRepository;
    private final RecipeRepository recipeRepository;
    private final RecipeReportRepository recipeReportRepository;
    private final RecipeAssembler recipeAssembler;

    //신고 처리 완료
    @Override
    public Page<PostReportRes> adminReportRecipe(Long recipeIdx, Long userIdx, String reason, Pageable pageable) {
        User user = this.userRepository.findByUserIdxAndIsEnable(userIdx, true).orElseThrow(UserNotFoundException::new);
        Reason recipeReason = Reason.getFoodCategoryByName(reason);
        Recipe recipe = this.recipeRepository.findByRecipeIdxAndIsEnable(recipeIdx, true).orElseThrow(RecipeNotFoundException::new);
        this.recipeReportRepository.save(this.recipeAssembler.toReportEntity(recipe, user, recipeReason));
        Page<RecipeReport> recipeReports = this.recipeReportRepository.findByRecipeAndUserAndReason(recipe, user, recipeReason,pageable);

        return this.recipeAssembler.toAdminReportEntity(recipeReports);
    }

}
