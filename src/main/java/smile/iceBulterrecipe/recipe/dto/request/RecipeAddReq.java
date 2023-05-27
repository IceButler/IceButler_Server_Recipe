package smile.iceBulterrecipe.recipe.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import smile.iceBulterrecipe.recipe.dto.response.CookeryRes;
import smile.iceBulterrecipe.recipe.dto.response.RecipeFoodRes;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecipeAddReq {
    private String imgKey;
    private String recipeName;
    private String recipeCategory;
    private int quantity;
    private Long leadTime;
    private List<RecipeFoodRes> recipeFoods;
    private List<CookeryRes> cookery;

}
