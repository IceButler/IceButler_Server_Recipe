package smile.iceBulterrecipe.recipe.exception;

public class RecipeListCategoryNotFoundException extends RuntimeException {
  public RecipeListCategoryNotFoundException(){
    super("QueryParam 내 카테고리를 확인해주세요. ");
  }
}
