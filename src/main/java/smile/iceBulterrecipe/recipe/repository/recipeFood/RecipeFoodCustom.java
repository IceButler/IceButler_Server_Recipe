package smile.iceBulterrecipe.recipe.repository.recipeFood;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import smile.iceBulterrecipe.recipe.dto.response.RecipeListRes;
import smile.iceBulterrecipe.recipe.dto.response.RecipeRes;
import smile.iceBulterrecipe.recipe.entity.Recipe;
import smile.iceBulterrecipe.recipe.entity.RecipeLike;

import java.util.List;
import java.util.UUID;

public interface RecipeFoodCustom {
    RecipeListRes getBookmarkRecipes(List<RecipeLike> bookmarkRecipeList, List<UUID> foodIdxes);
    Integer getPercentageOfFood(Long recipeIdx, List<UUID> foodIdxes);

    Page<RecipeRes> getRecipeByFridgeFoodList(Pageable pageable, List<UUID> foodIdxes);
}
