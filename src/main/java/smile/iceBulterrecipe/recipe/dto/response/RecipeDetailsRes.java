package smile.iceBulterrecipe.recipe.dto.response;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import smile.iceBulterrecipe.recipe.entity.Cookery;
import smile.iceBulterrecipe.recipe.entity.Recipe;
import smile.iceBulterrecipe.recipe.entity.RecipeFood;

import java.util.List;
import java.util.stream.Collectors;

@Data
@RequiredArgsConstructor
public class RecipeDetailsRes {
  private String recipeName;
  private String recipeCategory;
  private int quantity;
  private Long leadTime;
  private List<RecipeFoodRes> recipeFoods;
  private List<CookeryRes> cookery;


  public static RecipeDetailsRes toDto(Recipe recipe, List<RecipeFood> recipeFoods, List<Cookery> cookery) {
    RecipeDetailsRes recipeDetailsRes = new RecipeDetailsRes();
    recipeDetailsRes.recipeName = recipe.getRecipeName();
    recipeDetailsRes.recipeCategory = recipe.getRecipeCategory().getCategory();
    recipeDetailsRes.quantity = recipe.getQuantity();
    recipeDetailsRes.leadTime = recipe.getLeadTime();
    recipeDetailsRes.recipeFoods = recipeFoods.stream().map(m -> RecipeFoodRes.toDto(m.getFoodDetail())).collect(Collectors.toList());
    recipeDetailsRes.cookery = cookery.stream().map(m -> CookeryRes.toDto(m.getDescription(), m.getCookeryImgKey(), m.getNextIdx())).collect(Collectors.toList());
    return recipeDetailsRes;
  }
}
