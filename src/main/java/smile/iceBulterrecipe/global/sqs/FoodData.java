package smile.iceBulterrecipe.global.sqs;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import smile.iceBulterrecipe.food.entity.Food;

import java.util.UUID;

@NoArgsConstructor
@Getter
public class FoodData {
    private String foodName;
    private String foodImgKey;
    private String foodCategory;
    private UUID uuid;

    @Builder
    public FoodData(String foodName, String foodImgKey, String foodCategory, UUID uuid) {
        this.foodName = foodName;
        this.foodImgKey = foodImgKey;
        this.foodCategory = foodCategory;
        this.uuid = uuid;
    }

    public static FoodData toDto(Food food) {
        FoodData foodData = new FoodData();
        foodData.foodName = food.getFoodName();
        foodData.foodImgKey = food.getFoodImgKey();
        foodData.foodCategory = food.getFoodCategory().toString();
        foodData.uuid = food.getUuid();
        return foodData;
    }
}