package smile.iceBulterrecipe.recipe.repository.recipeLike;

import smile.iceBulterrecipe.recipe.entity.Recipe;
import smile.iceBulterrecipe.recipe.entity.RecipeLike;
import smile.iceBulterrecipe.user.entity.User;

import java.util.List;

public interface RecipeLikeCustom {
    List<Recipe> getPopularRecipe();
    List<RecipeLike> getBookmarkRecipe(User user, boolean status);
}
