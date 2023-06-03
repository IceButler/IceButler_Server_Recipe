package smile.iceBulterrecipe.recipe.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import smile.iceBulterrecipe.recipe.entity.Recipe;
import smile.iceBulterrecipe.recipe.entity.RecipeReport;
import smile.iceBulterrecipe.user.entity.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface RecipeReportRepository extends JpaRepository<RecipeReport, Long> {

    void deleteByRecipe(Recipe recipe);

    void deleteByUser(User user);

    Optional<RecipeReport> findByRecipeReportIdx(Long recipeReportIdx);

    Optional<RecipeReport> findByRecipeReportIdxAndIsEnable(Long recipeReportIdx,Boolean isEnable);

    Page<RecipeReport> findAllByIsEnableFalseOrderByCreatedAtDesc(Pageable pageable);

    Page<RecipeReport> findAllByIsEnableTrueOrderByCreatedAtDesc(Pageable pageable);

    Page<RecipeReport> findByRecipe_UserAndIsEnableFalseOrderByCreatedAtDesc(User user, Pageable pageable);

    Page<RecipeReport> findByRecipe_UserAndIsEnableTrueOrderByCreatedAtDesc(User user, Pageable pageable);

    Page<RecipeReport> findByRecipe_UserOrderByCreatedAtDesc(User user, Pageable pageable);

    List<RecipeReport> findByRecipe_UserUserIdxOrderByCreatedAtDesc(Long userIdx);
}
