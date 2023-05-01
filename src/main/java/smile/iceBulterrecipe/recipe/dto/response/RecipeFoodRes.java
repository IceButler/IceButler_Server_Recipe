package smile.iceBulterrecipe.recipe.dto.response;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import smile.iceBulterrecipe.recipe.entity.RecipeFood;

@Data
@RequiredArgsConstructor
public class RecipeFoodRes {
  private String foodDetail;

  @Builder
  public RecipeFoodRes(String foodDetail) {
    this.foodDetail = foodDetail;
  }

  public static RecipeFoodRes toDto(String foodDetail) {
    return RecipeFoodRes.builder()
            .foodDetail(foodDetail)
            .build();
  }
}
