package smile.iceBulterrecipe.global.entityListener;

import smile.iceBulterrecipe.global.utils.BeanUtils;
import smile.iceBulterrecipe.recipe.repository.RecipeRepository;
import smile.iceBulterrecipe.user.entity.User;

import javax.persistence.PreRemove;

public class UserEntityListener {
    @PreRemove
    public void onUpdate(User user){
        RecipeRepository recipeRepository = BeanUtils.getBean(RecipeRepository.class);
        recipeRepository.deleteByUser(user);
    }
}
