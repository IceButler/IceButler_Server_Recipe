package smile.iceBulterrecipe.recipe.entity;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum RecipeListCategory {
    FRIDGEFOODRECIPE("음식"),
    POPULARRECIPE("인기");

    String name;

    RecipeListCategory(String name) {
        this.name = name;
    }

    public static RecipeListCategory getFoodCategoryByName(String name){
        return Arrays.stream(RecipeListCategory.values())
                .filter(r -> r.getName().equals(name))
                .findAny().orElseThrow(); // todo: 일단 이넘으로 하는게 나은지 확인 후에 익셉션 추가할 것임요!
    }
}
