package smile.iceBulterrecipe.recipe.exception;

public class RecipeListCategoryNotFoundException extends RuntimeException {
  public RecipeListCategoryNotFoundException(){
    super("냉장고/인기 중에 카테고리를 선택해주세요. ");
  }
}
