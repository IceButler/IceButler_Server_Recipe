package smile.iceBulterrecipe.recipe.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;


import java.util.List;


@Getter
@AllArgsConstructor
public class BookMarkRecipeListRes {
    List<BookmarkRecipeRes> recipeList;

}
