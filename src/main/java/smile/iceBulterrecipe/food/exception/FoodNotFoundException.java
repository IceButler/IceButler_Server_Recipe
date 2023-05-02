package smile.iceBulterrecipe.food.exception;

public class FoodNotFoundException extends RuntimeException {
    public FoodNotFoundException(){
        super("존재하지 않는 음식입니다.");
    }
}