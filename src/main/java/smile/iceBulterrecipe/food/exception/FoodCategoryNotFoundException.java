package smile.iceBulterrecipe.food.exception;

public class FoodCategoryNotFoundException extends RuntimeException {
    public FoodCategoryNotFoundException(){
        super("존재하지 않는 카테고리입니다.");
    }
}
