package smile.iceBulterrecipe.recipe.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import smile.iceBulterrecipe.global.response.ResponseCustom;
import smile.iceBulterrecipe.recipe.exception.RecipeCategoryNotFoundException;

@Slf4j
@RestControllerAdvice
public class RecipeExceptionController {
    @ExceptionHandler(RecipeCategoryNotFoundException.class)
    public ResponseCustom<?> catchRecipeCategoryNotFoundException(RecipeCategoryNotFoundException e) {
        log.error(e.getMessage());
        return ResponseCustom.NOT_FOUND(e.getMessage());
    }
}