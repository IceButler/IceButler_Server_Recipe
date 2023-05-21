package smile.iceBulterrecipe.admin.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import smile.iceBulterrecipe.admin.exception.NotExistMemoException;
import smile.iceBulterrecipe.admin.exception.RecipeReportNotFoundException;
import smile.iceBulterrecipe.global.response.ResponseCustom;

@Slf4j
@RestControllerAdvice
public class AdminExceptionController {
    @ExceptionHandler(RecipeReportNotFoundException.class)
    public ResponseCustom<?> catchUserNotFoundException(RecipeReportNotFoundException e) {
        log.error(e.getMessage());
        return ResponseCustom.NOT_FOUND(e.getMessage());
    }
    @ExceptionHandler(NotExistMemoException.class)
    public ResponseCustom<?> catchUserNotFoundException(NotExistMemoException e) {
        log.error(e.getMessage());
        return ResponseCustom.NOT_FOUND(e.getMessage());
    }
}
