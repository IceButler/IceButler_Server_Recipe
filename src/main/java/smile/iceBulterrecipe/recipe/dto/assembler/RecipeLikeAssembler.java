package smile.iceBulterrecipe.recipe.dto.assembler;

import org.springframework.stereotype.Component;
import smile.iceBulterrecipe.recipe.entity.Recipe;
import smile.iceBulterrecipe.recipe.entity.RecipeLike;
import smile.iceBulterrecipe.user.entity.User;

@Component
public class RecipeLikeAssembler {

    public RecipeLike toEntity(User user, Recipe recipe) {
        return RecipeLike.builder()
                    .user(user)
                    .recipe(recipe)
                    .build();
    }
}
