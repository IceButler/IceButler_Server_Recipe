package smile.iceBulterrecipe.recipe.exception;

public class RecipeNotFoundException extends RuntimeException {
  public RecipeNotFoundException(){
    super("존재하지 않는 레시피입니다.");
  }
}
