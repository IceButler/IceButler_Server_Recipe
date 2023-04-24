package smile.iceBulterrecipe.recipe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import smile.iceBulterrecipe.recipe.entity.Recipe;
import smile.iceBulterrecipe.recipe.entity.RecipeReport;
import smile.iceBulterrecipe.user.entity.User;

@Repository
public interface RecipeReportRepository extends JpaRepository<RecipeReport, Long> {

    void deleteByRecipe(Recipe recipe);

    void deleteByUser(User user);
}
