package smile.iceBulterrecipe.food.entity;

import lombok.Getter;
import smile.iceBulterrecipe.food.exception.FoodCategoryNotFoundException;

import java.util.Arrays;

import static smile.iceBulterrecipe.global.Constant.Food.ICON_EXTENSION;
import static smile.iceBulterrecipe.global.Constant.Food.IMG_FOLDER;

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

    public static FoodCategory getFoodCategoryByName(String name){
        return Arrays.stream(FoodCategory.values())
                .filter(r -> r.getCategory().equals(name))
                .findAny().orElseThrow(FoodCategoryNotFoundException::new);
    }

    public static FoodCategory getFoodCategory(String foodCategory){
        return Arrays.stream(FoodCategory.values())
                .filter(r -> r.toString().equals(foodCategory))
                .findAny().orElseThrow(FoodCategoryNotFoundException::new);
    }

    public String getImage() {
        return IMG_FOLDER + this.name() + ICON_EXTENSION;
    }

}
