package smile.iceBulterrecipe.food.dto.assembler;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import smile.iceBulterrecipe.food.dto.request.FoodReq;
import smile.iceBulterrecipe.food.entity.Food;
import smile.iceBulterrecipe.food.entity.FoodCategory;
import smile.iceBulterrecipe.global.feign.dto.response.RecipeFridgeFoodListRes;
import smile.iceBulterrecipe.global.feign.dto.response.RecipeFridgeFoodListsRes;
import smile.iceBulterrecipe.recipe.dto.request.PostRecipeFoodReq;

import java.util.List;
import java.util.stream.Collectors;

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

    public Food toEntity(PostRecipeFoodReq food){
        return Food.builder()
                .foodName(food.getFoodName())
                .foodCategory(FoodCategory.PROCESSED_FOOD) // todo: 변경 필요, 일단 더미로 넣어둠
                .foodImgKey("food/MEAT.png")
                .build();
    }

    public List<Long> toFoodIdxes(RecipeFridgeFoodListsRes fridgeFoodList) {
        return fridgeFoodList.getFoodList().stream()
                .map(RecipeFridgeFoodListRes::getFoodIdx)
                .collect(Collectors.toList());
    }
}
