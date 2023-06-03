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
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class FoodAssembler {
    public Food toEntity(FoodReq food){
        return Food.builder()
                .foodName(food.getFoodName())
                .foodImgKey(food.getFoodImgKey())
                // TODO 큐에서 전달 받을 때 category value 값을 받아서 우선 변경, 해당 부분 통일 필요
                .foodCategory(FoodCategory.valueOf(food.getFoodCategory()))
                .uuid(UUID.randomUUID())
                .build();
    }

    public Food toEntity(PostRecipeFoodReq food, String foodCategory){
        FoodCategory category = FoodCategory.getFoodCategoryByName(foodCategory);
        return Food.builder()
                .foodName(food.getFoodName())
                .foodCategory(category)
                .foodImgKey(category.getImage())
                .uuid(UUID.randomUUID())
                .build();
    }

    public List<UUID> toFoodIdxes(RecipeFridgeFoodListsRes fridgeFoodList) {
        return fridgeFoodList.getFoodList().stream()
                .map(RecipeFridgeFoodListRes::getFoodIdx)
                .collect(Collectors.toList());
    }
}
