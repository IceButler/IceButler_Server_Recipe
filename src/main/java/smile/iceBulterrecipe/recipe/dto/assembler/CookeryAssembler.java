package smile.iceBulterrecipe.recipe.dto.assembler;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import smile.iceBulterrecipe.recipe.dto.request.PostRecipeCookeryReq;
import smile.iceBulterrecipe.recipe.dto.request.PostRecipeReq;
import smile.iceBulterrecipe.recipe.entity.Cookery;
import smile.iceBulterrecipe.recipe.entity.Recipe;
import smile.iceBulterrecipe.recipe.entity.RecipeCategory;
import smile.iceBulterrecipe.user.entity.User;

@Component
@RequiredArgsConstructor
public class CookeryAssembler {

    public Cookery toEntity(PostRecipeCookeryReq cookeryReq, Recipe recipe, Long nextIdx) {
        return Cookery.builder()
                .cookeryImgKey(cookeryReq.getCookeryImgKey())
                .nextIdx(nextIdx)
                .recipe(recipe)
                .description(cookeryReq.getCookeryDescription())
                .build();
    }
}
