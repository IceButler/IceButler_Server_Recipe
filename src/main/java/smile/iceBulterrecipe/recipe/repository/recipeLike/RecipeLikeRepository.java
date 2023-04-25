package smile.iceBulterrecipe.recipe.repository.recipeLike;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import smile.iceBulterrecipe.recipe.dto.assembler.RecipeAssembler;
import smile.iceBulterrecipe.recipe.entity.Recipe;
import smile.iceBulterrecipe.recipe.entity.RecipeLike;
import smile.iceBulterrecipe.user.entity.User;

@Repository
public interface RecipeLikeRepository extends JpaRepository<RecipeLike, Long>, RecipeLikeCustom{

    void deleteByRecipe(Recipe recipe);

    void deleteByUser(User user);

    Boolean existsByUserAndRecipe_RecipeIdxAndIsEnable(User user, Long recipeIdx, Boolean isEnable);
}
