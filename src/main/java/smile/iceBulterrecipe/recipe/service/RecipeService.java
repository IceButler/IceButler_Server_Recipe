package smile.iceBulterrecipe.recipe.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import smile.iceBulterrecipe.global.feign.dto.response.RecipeFridgeFoodListsRes;
import smile.iceBulterrecipe.recipe.dto.request.PostRecipeReq;
import smile.iceBulterrecipe.recipe.dto.response.BookmarkRes;
import smile.iceBulterrecipe.recipe.dto.response.MyRecipeListRes;
import smile.iceBulterrecipe.recipe.dto.response.RecipeListRes;
import smile.iceBulterrecipe.recipe.dto.response.RecipeRes;

public interface RecipeService{
    Page<RecipeRes> getPopularRecipeListsForFridge(Long userIdx, RecipeFridgeFoodListsRes listsRes, Pageable pageable);
    RecipeListRes getFridgeRecipeLists(Long userIdx, RecipeFridgeFoodListsRes listsRes);
    RecipeListRes getBookmarkRecipes(Long userIdx, RecipeFridgeFoodListsRes fridgeFoodList);
    BookmarkRes bookmarkRecipe(Long recipeIdx, Long userIdx);

    void postRecipe(PostRecipeReq recipeReq, Long userIdx);
    MyRecipeListRes getMyRecipe(Long userIdx);
}
