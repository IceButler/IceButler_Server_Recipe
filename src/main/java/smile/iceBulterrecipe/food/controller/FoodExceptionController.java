package smile.iceBulterrecipe.food.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import smile.iceBulterrecipe.food.exception.FoodNotFoundException;
import smile.iceBulterrecipe.global.response.ResponseCustom;


@Slf4j
@RestControllerAdvice
public class FoodExceptionController {
    @ExceptionHandler(FoodNotFoundException.class)
    public ResponseCustom<?> catchFoodNotFoundException(FoodNotFoundException e) {
        log.error(e.getMessage());
        return ResponseCustom.NOT_FOUND(e.getMessage());
    }
}
