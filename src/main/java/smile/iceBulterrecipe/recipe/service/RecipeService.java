package smile.iceBulterrecipe.recipe.service;

import smile.iceBulterrecipe.recipe.dto.response.RecipeMainListRes;

public interface RecipeService{
    RecipeMainListRes getRecipeMainLists(Long userIdx, String recipeListCategory);
}
