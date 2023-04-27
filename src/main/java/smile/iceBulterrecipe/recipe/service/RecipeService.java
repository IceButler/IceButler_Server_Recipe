package smile.iceBulterrecipe.recipe.service;

import smile.iceBulterrecipe.global.feign.dto.response.RecipeFridgeFoodListsRes;
import smile.iceBulterrecipe.recipe.dto.response.RecipeListRes;

public interface RecipeService{
    RecipeListRes getPopularRecipeListsForFridge(Long userIdx, RecipeFridgeFoodListsRes listsRes);
    RecipeListRes getFridgeRecipeLists(Long userIdx, RecipeFridgeFoodListsRes listsRes);
    RecipeListRes getBookmarkRecipes(Long userIdx, RecipeFridgeFoodListsRes fridgeFoodList);
    void bookmarkRecipe(Long recipeIdx, Long userIdx);
}
