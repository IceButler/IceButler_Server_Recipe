package smile.iceBulterrecipe.recipe.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import smile.iceBulterrecipe.global.feign.dto.response.RecipeFridgeFoodListsRes;
import smile.iceBulterrecipe.recipe.dto.request.PostRecipeReq;
import smile.iceBulterrecipe.recipe.dto.response.*;

public interface RecipeService{
    Page<RecipeRes> getPopularRecipeListsForFridge(Long userIdx, RecipeFridgeFoodListsRes listsRes, Pageable pageable);
    Page<RecipeRes> getFridgeRecipeLists(Long userIdx, RecipeFridgeFoodListsRes listsRes, Pageable pageable);
    Page<RecipeRes> getBookmarkRecipes(Long userIdx, RecipeFridgeFoodListsRes fridgeFoodList, Pageable pageable);
    BookmarkRes bookmarkRecipe(Long recipeIdx, Long userIdx);

    void postRecipe(PostRecipeReq recipeReq, Long userIdx);
    Page<MyRecipeRes> getMyRecipe(Long userIdx, Pageable pageable);

    Page<RecipeRes> getSearchRecipeListForRecipe(Long userIdx, RecipeFridgeFoodListsRes data, String keyword, Pageable pageable);
    void deleteMyRecipe(Long recipeIdx, Long userIdx);

    Page<RecipeRes> getSearchRecipeListForFood(Long userIdx, RecipeFridgeFoodListsRes data, String keyword, Pageable pageable);

    void reportRecipe(Long recipeIdx, Long userIdx, String reason);


    void updateRecipe(PostRecipeReq recipeReq, Long userIdx, Long recipeIdx);
}
