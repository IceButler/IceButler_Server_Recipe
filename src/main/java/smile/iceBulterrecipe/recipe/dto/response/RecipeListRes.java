package smile.iceBulterrecipe.recipe.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class RecipeListRes {
    List<RecipeRes> recipeMainResList;

    public static RecipeListRes toDto(List<RecipeRes> recipe) {
        return new RecipeListRes(recipe);
    }
}
