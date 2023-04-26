package smile.iceBulterrecipe.recipe.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import smile.iceBulterrecipe.global.utils.AwsS3ImageUrlUtils;
import smile.iceBulterrecipe.recipe.entity.Recipe;

import java.util.List;

@Getter
@RequiredArgsConstructor
@AllArgsConstructor
public class BookmarkRecipeRes {
    private Long recipeIdx;
    private String recipeImgUrl;
    private String recipeName;
    private String recipeCategory;
    private double percentageOfFood;


    public static BookmarkRecipeRes toDto(Recipe recipe, double percentage) {
        return new BookmarkRecipeRes(
                recipe.getRecipeIdx(),
                AwsS3ImageUrlUtils.toUrl(recipe.getRecipeImgKey()),
                recipe.getRecipeName(),
                recipe.getRecipeCategory().getCategory(),
                percentage
        );
    }
}
