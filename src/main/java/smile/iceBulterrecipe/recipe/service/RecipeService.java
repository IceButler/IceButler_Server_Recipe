package smile.iceBulterrecipe.recipe.service;

import smile.iceBulterrecipe.global.feign.dto.response.RecipeFridgeFoodListsRes;
import smile.iceBulterrecipe.recipe.dto.response.BookMarkRecipeListRes;
import smile.iceBulterrecipe.recipe.dto.response.RecipeMainListRes;

public interface RecipeService{
    RecipeMainListRes getPopularRecipeLists(Long userIdx);
    RecipeMainListRes getFridgeRecipeLists(Long userIdx, Long fridgeIdx);
    RecipeMainListRes getMultiFridgeRecipeLists(Long userIdx, Long multiFridgeIdx);
//    RecipeMainListRes getRecipeMainLists(Long userIdx, String recipeListCategory);
    BookMarkRecipeListRes getBookmarkRecipes(Long userIdx, RecipeFridgeFoodListsRes fridgeFoodList);
    void bookmarkRecipe(Long recipeIdx, Long userIdx);
}
