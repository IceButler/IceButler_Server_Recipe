package smile.iceBulterrecipe.recipe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import smile.iceBulterrecipe.recipe.entity.Recipe;
import smile.iceBulterrecipe.recipe.entity.RecipeFood;

@Repository
public interface RecipeFoodRepository extends JpaRepository<RecipeFood, Long> {

    void deleteByRecipe(Recipe recipe);
}
