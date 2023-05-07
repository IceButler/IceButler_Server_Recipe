package smile.iceBulterrecipe.recipe.exception;

public class InvalidRecipeUserException extends RuntimeException {
  public InvalidRecipeUserException(){
    super("사용자가 작성한 레시피가 아닙니다.");
  }
}
