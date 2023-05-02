package smile.iceBulterrecipe.recipe.dto.assembler;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import smile.iceBulterrecipe.food.entity.Food;
import smile.iceBulterrecipe.recipe.dto.request.PostRecipeFoodReq;
import smile.iceBulterrecipe.recipe.dto.request.PostRecipeReq;
import smile.iceBulterrecipe.recipe.entity.Recipe;
import smile.iceBulterrecipe.recipe.entity.RecipeCategory;
import smile.iceBulterrecipe.recipe.entity.RecipeFood;
import smile.iceBulterrecipe.user.entity.User;

@Component
@RequiredArgsConstructor
public class RecipeFoodAssembler {

    public RecipeFood toEntity(PostRecipeFoodReq recipeReq, Food food, Recipe recipe) {
        return RecipeFood.builder()
                .food(food)
                .recipe(recipe)
                .foodDetail(recipeReq.getFoodDetail())
                .build();
    }
}
