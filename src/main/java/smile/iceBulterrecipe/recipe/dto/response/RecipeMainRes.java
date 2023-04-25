package smile.iceBulterrecipe.recipe.dto.response;

import com.querydsl.core.annotations.QueryProjection;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

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

    public void setPercentageOfFood(double percentageOfFood) {
        this.percentageOfFood = percentageOfFood;
    }

    public void setRecipeLikeStatus(Boolean recipeLikeStatus) {
        this.recipeLikeStatus = recipeLikeStatus;
    }
}
