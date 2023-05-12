package smile.iceBulterrecipe.recipe.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import smile.iceBulterrecipe.global.feign.dto.response.RecipeFridgeFoodListsRes;
import smile.iceBulterrecipe.recipe.dto.request.PostRecipeReq;
import smile.iceBulterrecipe.recipe.dto.response.*;

public interface RecipeService{
    Page<RecipeRes> getPopularRecipeListsForFridge(Long userIdx, RecipeFridgeFoodListsRes listsRes, Pageable pageable);
    Page<RecipeRes> getFridgeRecipeLists(Long userIdx, RecipeFridgeFoodListsRes listsRes, Pageable pageable);
    RecipeListRes getBookmarkRecipes(Long userIdx, RecipeFridgeFoodListsRes fridgeFoodList);
    BookmarkRes bookmarkRecipe(Long recipeIdx, Long userIdx);

    void postRecipe(PostRecipeReq recipeReq, Long userIdx);
    Page<MyRecipeRes> getMyRecipe(Long userIdx, Pageable pageable);

    Page<RecipeRes> getSearchRecipeListForRecipe(Long userIdx, String keyword, Pageable pageable);
    void deleteMyRecipe(Long recipeIdx, Long userIdx);

    Page<RecipeRes> getSearchRecipeListForFood(Long userIdx, String keyword, Pageable pageable);
}
