package smile.iceBulterrecipe.food.dto.request;

import lombok.Getter;

@Getter
public class FoodReq {
    private Long foodIdx;
    private String foodName;
    private String foodImgKey;
    private String foodCategory;
}
