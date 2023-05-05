package smile.iceBulterrecipe.recipe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import smile.iceBulterrecipe.recipe.entity.Recipe;
import smile.iceBulterrecipe.user.entity.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {
    void deleteByUser(User user);

    Optional<Recipe> findByRecipeIdxAndIsEnable(Long recipeIdx, boolean status);

    List<Recipe> findByUserAndIsEnable(User user, boolean status);
}
