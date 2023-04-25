package smile.iceBulterrecipe.recipe.dto.assembler;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import smile.iceBulterrecipe.global.utils.AwsS3ImageUrlUtils;
import smile.iceBulterrecipe.recipe.dto.response.RecipeMainRes;
import smile.iceBulterrecipe.recipe.entity.Recipe;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class RecipeAssembler {
    public List<RecipeMainRes> toBasicMainDTO(List<Recipe> recipes) {
        return recipes.stream()
                .map(p -> new RecipeMainRes(p.getRecipeIdx(), AwsS3ImageUrlUtils.toUrl(p.getRecipeImgKey()), p.getRecipeName(), p.getRecipeCategory().getCategory(), 0.0,
                        null))
                .collect(Collectors.toList());
    }

    public void toUserLikeStatus(RecipeMainRes res, Boolean isEnable) {
        res.setRecipeLikeStatus(isEnable);
    }
}
