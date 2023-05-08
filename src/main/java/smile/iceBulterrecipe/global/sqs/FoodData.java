package smile.iceBulterrecipe.global.sqs;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import smile.iceBulterrecipe.food.entity.Food;

@NoArgsConstructor
@Getter
public class FoodData {
    private Long foodIdx;
    private String foodName;
    private String foodImgKey;
    private String foodCategory;

    @Builder
    public FoodData(Long foodIdx, String foodName, String foodImgKey, String foodCategory) {
        this.foodIdx = foodIdx;
        this.foodName = foodName;
        this.foodImgKey = foodImgKey;
        this.foodCategory = foodCategory;
    }

    public static FoodData toDto(Food food) {
        FoodData foodData = new FoodData();
        foodData.foodIdx = food.getFoodIdx();
        foodData.foodName = food.getFoodName();
        foodData.foodImgKey = food.getFoodImgKey();
        foodData.foodCategory = food.getFoodCategory().toString();
        return foodData;
    }
}