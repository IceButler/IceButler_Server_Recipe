package smile.iceBulterrecipe.recipe.dto.response;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import smile.iceBulterrecipe.global.utils.AwsS3ImageUrlUtils;
import smile.iceBulterrecipe.recipe.entity.Cookery;
import smile.iceBulterrecipe.recipe.entity.Recipe;
import smile.iceBulterrecipe.recipe.entity.RecipeFood;

import java.util.List;
import java.util.stream.Collectors;

@Data
@RequiredArgsConstructor
public class RecipeDetailsRes {
  private String recipeImgUrl;
  private String recipeName;
  private String recipeCategory;
  private int quantity;
  private Long leadTime;
  private List<RecipeFoodRes> recipeFoods;
  private List<CookeryRes> cookery;
  private Boolean isSubscribe;


  public static RecipeDetailsRes toDto(Recipe recipe, List<RecipeFood> recipeFoods, List<Cookery> cookery, Boolean isSubscribe) {
    RecipeDetailsRes recipeDetailsRes = new RecipeDetailsRes();
    recipeDetailsRes.isSubscribe = isSubscribe;
    recipeDetailsRes.recipeImgUrl = AwsS3ImageUrlUtils.toUrl(recipe.getRecipeImgKey());
    recipeDetailsRes.recipeName = recipe.getRecipeName();
    recipeDetailsRes.recipeCategory = recipe.getRecipeCategory().getCategory();
    recipeDetailsRes.quantity = recipe.getQuantity();
    recipeDetailsRes.leadTime = recipe.getLeadTime();
    recipeDetailsRes.recipeFoods = recipeFoods.stream().map(m -> RecipeFoodRes.toDto(m.getFoodDetail(), m.getFood().getFoodName())).collect(Collectors.toList());
    recipeDetailsRes.cookery = cookery.stream().map(m -> CookeryRes.toDto(m.getDescription(), m.getCookeryImgKey(), m.getNextIdx())).collect(Collectors.toList());
    return recipeDetailsRes;
  }
}
