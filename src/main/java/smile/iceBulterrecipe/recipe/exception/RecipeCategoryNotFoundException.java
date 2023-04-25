package smile.iceBulterrecipe.recipe.exception;

public class RecipeCategoryNotFoundException extends RuntimeException {
  public RecipeCategoryNotFoundException(){
    super("존재하지 않는 레시피 카테고리입니다.");
  }
}
