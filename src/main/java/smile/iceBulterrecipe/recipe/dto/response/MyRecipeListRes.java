package smile.iceBulterrecipe.recipe.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import smile.iceBulterrecipe.recipe.entity.Recipe;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
public class MyRecipeListRes {
    List<MyRecipeRes> myRecipeList;

    public static MyRecipeListRes toDto(List<Recipe> recipe) {
        return new MyRecipeListRes(recipe.stream()
                .map(MyRecipeRes::toDto)
                .collect(Collectors.toList()));
    }
}
