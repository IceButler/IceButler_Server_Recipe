package smile.iceBulterrecipe.recipe.service;

import smile.iceBulterrecipe.global.feign.dto.response.RecipeFridgeFoodListsRes;
import smile.iceBulterrecipe.recipe.dto.response.BookMarkRecipeListRes;
import smile.iceBulterrecipe.recipe.dto.response.RecipeMainListRes;

public interface RecipeService{
    RecipeMainListRes getPopularRecipeListsForFridge(Long userIdx, RecipeFridgeFoodListsRes listsRes);
    RecipeMainListRes getFridgeRecipeLists(Long userIdx, RecipeFridgeFoodListsRes listsRes);

    BookMarkRecipeListRes getBookmarkRecipes(Long userIdx, RecipeFridgeFoodListsRes fridgeFoodList);
}
