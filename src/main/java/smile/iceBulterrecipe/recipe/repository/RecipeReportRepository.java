package smile.iceBulterrecipe.recipe.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import smile.iceBulterrecipe.recipe.Reason;
import smile.iceBulterrecipe.recipe.entity.Recipe;
import smile.iceBulterrecipe.recipe.entity.RecipeReport;
import smile.iceBulterrecipe.user.entity.User;

import java.util.Optional;

@Repository
public interface RecipeReportRepository extends JpaRepository<RecipeReport, Long> {

    void deleteByRecipe(Recipe recipe);

    void deleteByUser(User user);

//    Page<RecipeReport> findByRecipeAndUserAndReason(Recipe recipe, User user, Reason reason,Pageable pageable);
    Optional<RecipeReport> findByRecipeReportIdx(Long recipeReportIdx);

    Optional<RecipeReport> findByRecipeReportIdxAndIsEnable(Long recipeReportIdx,Boolean isEnable);


}
