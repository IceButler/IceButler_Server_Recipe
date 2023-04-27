package smile.iceBulterrecipe.recipe.dto.response;

import com.querydsl.core.annotations.QueryProjection;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import smile.iceBulterrecipe.global.utils.AwsS3ImageUrlUtils;
import smile.iceBulterrecipe.recipe.entity.Recipe;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RecipeMainRes {
    private Long recipeIdx;
    private String recipeImgUrl;
    private String recipeName;
    private String recipeCategory;
    private double percentageOfFood;
    private Boolean recipeLikeStatus;

    public static RecipeMainRes toDto(Recipe recipe, double percentage, Boolean recipeLikeStatus) {
        return new RecipeMainRes(
                recipe.getRecipeIdx(),
                AwsS3ImageUrlUtils.toUrl(recipe.getRecipeImgKey()),
                recipe.getRecipeName(),
                recipe.getRecipeCategory().getCategory(),
                percentage,
                recipeLikeStatus
        );
    }

    @QueryProjection
    public RecipeMainRes(Long recipeIdx, String recipeImgUrl, String recipeName, String recipeCategory, double percentageOfFood) {
        this.recipeIdx = recipeIdx;
        this.recipeImgUrl = recipeImgUrl;
        this.recipeName = recipeName;
        this.recipeCategory = recipeCategory;
        this.percentageOfFood = percentageOfFood;
    }

    public void setRecipeLikeStatus(Boolean recipeLikeStatus) {
        this.recipeLikeStatus = recipeLikeStatus;
    }
}
