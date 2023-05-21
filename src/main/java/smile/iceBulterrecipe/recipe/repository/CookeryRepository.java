package smile.iceBulterrecipe.recipe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import smile.iceBulterrecipe.recipe.entity.Cookery;
import smile.iceBulterrecipe.recipe.entity.Recipe;
import smile.iceBulterrecipe.recipe.entity.RecipeReport;

import java.util.List;

@Repository
public interface CookeryRepository extends JpaRepository<Cookery, Long> {

    void deleteByRecipe(Recipe recipe);

    //    List<Cookery> findByRecipeAndIsEnable(Recipe recipe, Boolean status);
    List<Cookery> findByRecipeAndIsEnableOrderByNextIdx(Recipe recipe, Boolean status);

}
