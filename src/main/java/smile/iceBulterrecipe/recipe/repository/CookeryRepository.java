package smile.iceBulterrecipe.recipe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import smile.iceBulterrecipe.recipe.entity.Cookery;
import smile.iceBulterrecipe.recipe.entity.Recipe;

@Repository
public interface CookeryRepository extends JpaRepository<Cookery, Long> {

    void deleteByRecipe(Recipe recipe);
}
