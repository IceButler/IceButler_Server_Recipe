package smile.iceBulterrecipe.recipe.repository.recipeFood;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import smile.iceBulterrecipe.recipe.entity.Recipe;
import smile.iceBulterrecipe.recipe.entity.RecipeFood;

import java.util.List;

@Repository
public interface RecipeFoodRepository extends JpaRepository<RecipeFood, Long>, RecipeFoodCustom {

    void deleteByRecipe(Recipe recipe);

    List<RecipeFood> findByRecipeAndIsEnable(Recipe recipe, Boolean status);
}
