package smile.iceBulterrecipe.recipe.dto.assembler;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import smile.iceBulterrecipe.recipe.dto.request.PostRecipeReq;
import smile.iceBulterrecipe.recipe.entity.Recipe;
import smile.iceBulterrecipe.recipe.entity.RecipeCategory;
import smile.iceBulterrecipe.user.entity.User;

@Component
@RequiredArgsConstructor
public class RecipeAssembler {

    public Recipe toEntity(PostRecipeReq recipeReq, User user) {
        return Recipe.builder()
                .recipeName(recipeReq.getRecipeName())
                .recipeImgKey(recipeReq.getRecipeImgKey())
                .recipeCategory(RecipeCategory.getFoodCategoryByName(recipeReq.getRecipeCategory()))
                .quantity(recipeReq.getQuantity())
                .leadTime(recipeReq.getLeadTime())
                .user(user)
                .build();

    }
}
