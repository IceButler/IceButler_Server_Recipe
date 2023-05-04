package smile.iceBulterrecipe.recipe.repository.recipeFood;

import smile.iceBulterrecipe.recipe.dto.response.RecipeListRes;
import smile.iceBulterrecipe.recipe.entity.Recipe;

import java.util.List;

public interface RecipeFoodCustom {

    RecipeListRes getBookmarkRecipes(List<Recipe> bookmarkRecipeList, List<Long> foodIdxes);
    Integer getPercentageOfFood(Long recipeIdx, List<Long> foodIdxes);

    List<Recipe> getRecipeByFridgeFoodList(List<Long> foodIdxes);
}
