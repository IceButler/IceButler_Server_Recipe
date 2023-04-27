package smile.iceBulterrecipe.recipe.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import smile.iceBulterrecipe.recipe.entity.RecipeLike;

@Getter
@AllArgsConstructor
public class BookmarkRes {
    private boolean status;

    public static BookmarkRes toDto(RecipeLike recipeLike) {
        return new BookmarkRes(recipeLike.getIsEnable());
    }
}
