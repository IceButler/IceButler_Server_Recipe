package smile.iceBulterrecipe.recipe.repository.recipeLike;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import smile.iceBulterrecipe.recipe.dto.response.RecipeRes;
import smile.iceBulterrecipe.recipe.entity.RecipeLike;
import smile.iceBulterrecipe.user.entity.User;

import java.util.List;
import java.util.UUID;

public interface RecipeLikeCustom {
    Page<RecipeRes> getPopularRecipe(Pageable pageable, List<UUID> foodIdxes);
    List<RecipeLike> getBookmarkRecipe(User user, boolean status);
}
