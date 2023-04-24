package smile.iceBulterrecipe.food.dto.assembler;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import smile.iceBulterrecipe.food.dto.request.FoodReq;
import smile.iceBulterrecipe.food.entity.Food;
import smile.iceBulterrecipe.food.entity.FoodCategory;

@Component
@RequiredArgsConstructor
public class FoodAssembler {
    public Food toEntity(FoodReq food){
        return Food.builder()
                .foodIdx(food.getFoodIdx())
                .foodName(food.getFoodName())
                .foodImgKey(food.getFoodImgKey())
                .foodCategory(FoodCategory.getFoodCategoryByName(food.getFoodName()))
                .build();
    }
}
