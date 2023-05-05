package smile.iceBulterrecipe.recipe.dto.response;

import com.querydsl.core.annotations.QueryProjection;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import smile.iceBulterrecipe.global.utils.AwsS3ImageUrlUtils;
import smile.iceBulterrecipe.recipe.entity.Recipe;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RecipeRes {
    private Long recipeIdx;
    private String recipeImgUrl;
    private String recipeName;
    private String recipeCategory;
    private Integer percentageOfFood;



    private Boolean recipeLikeStatus;

    public static RecipeRes toDto(Recipe recipe, Integer percentage, Boolean recipeLikeStatus) {
        return new RecipeRes(
                recipe.getRecipeIdx(),
                AwsS3ImageUrlUtils.toUrl(recipe.getRecipeImgKey()),
                recipe.getRecipeName(),
                recipe.getRecipeCategory().getCategory(),
                percentage,
                recipeLikeStatus
        );
    }

    public static RecipeRes toDto(Recipe recipe) {
        return new RecipeRes(
                recipe.getRecipeIdx(),
                AwsS3ImageUrlUtils.toUrl(recipe.getRecipeImgKey()),
                recipe.getRecipeName(),
                recipe.getRecipeCategory().getCategory(),
                null,
                null
        );
    }

    @QueryProjection
    public RecipeRes(Long recipeIdx, String recipeImgUrl, String recipeName, String recipeCategory, Integer percentageOfFood) {
        this.recipeIdx = recipeIdx;
        this.recipeImgUrl = recipeImgUrl;
        this.recipeName = recipeName;
        this.recipeCategory = recipeCategory;
        this.percentageOfFood = percentageOfFood;
    }

    public void setRecipeLikeStatus(Boolean recipeLikeStatus) {
        this.recipeLikeStatus = recipeLikeStatus;
    }
    public void setPercentageOfFood(Integer percentageOfFood) {
        this.percentageOfFood = percentageOfFood;
    }

}
