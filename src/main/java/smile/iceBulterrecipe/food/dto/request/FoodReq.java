package smile.iceBulterrecipe.food.dto.request;

import lombok.Builder;
import lombok.Getter;
import smile.iceBulterrecipe.global.sqs.FoodData;

import java.util.UUID;

@Getter
public class FoodReq {
    private Long foodIdx;
    private String foodName;
    private String foodImgKey;
    private String foodCategory;
    private UUID uuid;

    @Builder
    public FoodReq(String foodName, String foodImgKey, String foodCategory, UUID uuid) {
        this.foodName = foodName;
        this.foodImgKey = foodImgKey;
        this.foodCategory = foodCategory;
        this.uuid = uuid;
    }

    public static FoodReq toEntity(FoodData foodData) {
        return FoodReq.builder()
                .foodName(foodData.getFoodName())
                .foodImgKey(foodData.getFoodImgKey())
                .foodCategory(foodData.getFoodCategory())
                .uuid(UUID.fromString(foodData.getUuid()))
                .build();
    }
}
