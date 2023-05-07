package smile.iceBulterrecipe.recipe.repository.recipe;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import smile.iceBulterrecipe.recipe.entity.Recipe;

public interface RecipeCustom {
    Page<Recipe> findByFoodNameContainingAndIsEnableHavingRecipe(String keyword, Pageable pageable);
}
