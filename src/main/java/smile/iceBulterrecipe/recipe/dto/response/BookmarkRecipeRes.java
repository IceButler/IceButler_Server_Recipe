package smile.iceBulterrecipe.recipe.dto.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class BookmarkRecipeRes {
    private Long recipeIdx;
    private String recipeImgUrl;
    private String recipeName;
    private String recipeCategory;
    private double percentageOfFood;

    
}
