package smile.iceBulterrecipe.recipe;

import lombok.Getter;
import smile.iceBulterrecipe.recipe.entity.RecipeCategory;
import smile.iceBulterrecipe.recipe.exception.RecipeCategoryNotFoundException;

import java.util.Arrays;

@Getter
public enum Reason {

    JUNK("홍보/도배"),
    PORN("음란물/유해한 정보"),
    POOR_CONTENT("내용이 부실함"),
    NOT_FIT("게시글 성격에 부적합함");

    private final String reason;

    private Reason(String reason) {
        this.reason = reason;
    }

    public static Reason getFoodCategoryByName(String name){
        return Arrays.stream(Reason.values())
                .filter(r -> r.getReason().equals(name))
                .findAny().orElseThrow(RecipeCategoryNotFoundException::new);
    }
}
