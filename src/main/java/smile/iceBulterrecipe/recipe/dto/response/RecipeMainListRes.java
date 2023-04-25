package smile.iceBulterrecipe.recipe.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
public class RecipeMainListRes {
    List<RecipeMainRes> recipeMainResList;

    public static RecipeMainListRes toDto(List<RecipeMainRes> recipe) {
        return new RecipeMainListRes(recipe);
    }
}
