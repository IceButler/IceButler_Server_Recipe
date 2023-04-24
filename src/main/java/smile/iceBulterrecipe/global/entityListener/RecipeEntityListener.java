package smile.iceBulterrecipe.global.entityListener;

import smile.iceBulterrecipe.global.utils.BeanUtils;
import smile.iceBulterrecipe.recipe.entity.Recipe;
import smile.iceBulterrecipe.recipe.repository.CookeryRepository;
import smile.iceBulterrecipe.recipe.repository.RecipeFoodRepository;
import smile.iceBulterrecipe.recipe.repository.recipeLike.RecipeLikeRepository;
import smile.iceBulterrecipe.recipe.repository.RecipeReportRepository;

import javax.persistence.PreRemove;

public class RecipeEntityListener {
    @PreRemove
    public void onUpdate(Recipe recipe){
        CookeryRepository cookeryRepository = BeanUtils.getBean(CookeryRepository.class);
        cookeryRepository.deleteByRecipe(recipe);
        RecipeFoodRepository recipeFoodRepository = BeanUtils.getBean(RecipeFoodRepository.class);
        recipeFoodRepository.deleteByRecipe(recipe);
        RecipeLikeRepository recipeLikeRepository = BeanUtils.getBean(RecipeLikeRepository.class);
        recipeLikeRepository.deleteByRecipe(recipe);
        RecipeReportRepository recipeReportRepository = BeanUtils.getBean(RecipeReportRepository.class);
        recipeReportRepository.deleteByRecipe(recipe);
    }
}
