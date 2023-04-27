package smile.iceBulterrecipe.recipe.repository.recipeFood;

import smile.iceBulterrecipe.recipe.dto.response.BookMarkRecipeListRes;
import smile.iceBulterrecipe.recipe.dto.response.RecipeMainListRes;
import smile.iceBulterrecipe.recipe.entity.Recipe;

import java.util.List;

public interface RecipeFoodCustom {

    BookMarkRecipeListRes getBookmarkRecipes(List<Recipe> bookmarkRecipeList, List<Long> foodIdxes);
    long getPercentageOfFood(Recipe recipe, List<Long> foodIdxes);

    List<Recipe> getRecipeByFridgeFoodList(List<Long> foodIdxes);
}
