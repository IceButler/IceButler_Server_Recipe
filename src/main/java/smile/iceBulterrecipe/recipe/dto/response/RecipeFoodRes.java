package smile.iceBulterrecipe.recipe.dto.response;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import smile.iceBulterrecipe.recipe.entity.RecipeFood;

@Data
@RequiredArgsConstructor
public class RecipeFoodRes {
  private String foodName;
  private String foodDetail;

  @Builder
  public RecipeFoodRes(String foodName, String foodDetail) {
    this.foodName = foodName;
    this.foodDetail = foodDetail;
  }

  public static RecipeFoodRes toDto(String foodDetail, String foodName) {
    return RecipeFoodRes.builder()
            .foodName(foodName)
            .foodDetail(foodDetail)
            .build();
  }
}
