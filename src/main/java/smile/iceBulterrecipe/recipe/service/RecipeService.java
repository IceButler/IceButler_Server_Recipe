package smile.iceBulterrecipe.recipe.service;

import smile.iceBulterrecipe.recipe.dto.response.RecipeMainListRes;

public interface RecipeService{
    RecipeMainListRes getPopularRecipeLists(Long userIdx);
    RecipeMainListRes getFridgeRecipeLists(Long userIdx, Long fridgeIdx);
    RecipeMainListRes getMultiFridgeRecipeLists(Long userIdx, Long multiFridgeIdx);
}
