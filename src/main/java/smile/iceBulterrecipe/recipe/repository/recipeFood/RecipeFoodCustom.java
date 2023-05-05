package smile.iceBulterrecipe.recipe.repository.recipeFood;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import smile.iceBulterrecipe.recipe.dto.response.RecipeListRes;
import smile.iceBulterrecipe.recipe.dto.response.RecipeRes;
import smile.iceBulterrecipe.recipe.entity.Recipe;
import smile.iceBulterrecipe.recipe.entity.RecipeLike;

import java.util.List;

public interface RecipeFoodCustom {
    RecipeListRes getBookmarkRecipes(List<RecipeLike> bookmarkRecipeList, List<Long> foodIdxes);
    Integer getPercentageOfFood(Long recipeIdx, List<Long> foodIdxes);

    Page<RecipeRes> getRecipeByFridgeFoodList(Pageable pageable, List<Long> foodIdxes);
}
