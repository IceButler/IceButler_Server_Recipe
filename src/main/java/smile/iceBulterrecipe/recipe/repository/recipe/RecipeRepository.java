package smile.iceBulterrecipe.recipe.repository.recipe;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import smile.iceBulterrecipe.recipe.entity.Recipe;
import smile.iceBulterrecipe.recipe.entity.RecipeReport;
import smile.iceBulterrecipe.user.entity.User;

import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long>, RecipeCustom {
    void deleteByUser(User user);

    Optional<Recipe> findByRecipeIdxAndIsEnable(Long recipeIdx, boolean status);

    Page<Recipe> findByUserAndIsEnable(User user, boolean status, Pageable pageable);

    Page<Recipe> findByRecipeNameContainingAndIsEnable(String keyword, boolean status, Pageable pageable);


}
