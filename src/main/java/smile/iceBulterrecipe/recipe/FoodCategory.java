package smile.iceBulterrecipe.recipe;

import lombok.Getter;

@Getter
public enum FoodCategory {
    MEAT("육류"),
    FRUIT("과일"),
    VEGETABLE("채소"),
    BEVERAGE("음료"),
    AQUATIC_PRODUCTS("수산물"),
    BANCHAN("반찬"),
    SNACK("간식"),
    CONDIMENT("조미료"),
    PROCESSED_FOOD("가공식품"),
    ETC("기타");

    private final String category;

    private FoodCategory(String category) {
        this.category = category;
    }
}
