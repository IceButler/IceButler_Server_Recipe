package smile.iceBulterrecipe.recipe.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import smile.iceBulterrecipe.recipe.entity.Recipe;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
public class BookMarkRecipeListRes {
    List<BookmarkRecipeRes> recipeList;

    public static BookMarkRecipeListRes toDto(List<Recipe> bookmarkRecipeList) {
        return new BookMarkRecipeListRes(bookmarkRecipeList.stream()
                .map(BookmarkRecipeRes::toDto)
                .collect(Collectors.toList()));
    }
}
