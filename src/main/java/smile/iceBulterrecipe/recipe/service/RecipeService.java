package smile.iceBulterrecipe.recipe.service;

import smile.iceBulterrecipe.global.feign.dto.response.RecipeFridgeFoodListsRes;
import smile.iceBulterrecipe.recipe.dto.request.PostRecipeReq;
import smile.iceBulterrecipe.recipe.dto.response.BookmarkRes;
import smile.iceBulterrecipe.recipe.dto.response.RecipeListRes;

public interface RecipeService{
    RecipeListRes getPopularRecipeListsForFridge(Long userIdx, RecipeFridgeFoodListsRes listsRes);
    RecipeListRes getFridgeRecipeLists(Long userIdx, RecipeFridgeFoodListsRes listsRes);
    RecipeListRes getBookmarkRecipes(Long userIdx, RecipeFridgeFoodListsRes fridgeFoodList);
    BookmarkRes bookmarkRecipe(Long recipeIdx, Long userIdx);

    void postRecipe(PostRecipeReq recipeReq, Long userIdx);
}
