package smile.iceBulterrecipe.user.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import smile.iceBulterrecipe.global.response.ResponseCustom;
import smile.iceBulterrecipe.user.exception.AuthAnnotationIsNowhereException;
import smile.iceBulterrecipe.user.exception.TokenExpirationException;
import smile.iceBulterrecipe.user.exception.UserNotFoundException;

@Slf4j
@RestControllerAdvice
public class UserExceptionController {
    /**
     * User Exceptions
     */
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseCustom<?> catchUserNotFoundException(UserNotFoundException e) {
        log.error(e.getMessage());
        return ResponseCustom.NOT_FOUND(e.getMessage());
    }

    @ExceptionHandler(TokenExpirationException.class)
    public ResponseCustom<?> catchTokenExpirationException(TokenExpirationException e) {
        log.error(e.getMessage());
        return ResponseCustom.BAD_REQUEST(e.getMessage());
    }

    @ExceptionHandler(AuthAnnotationIsNowhereException.class)
    public ResponseCustom<?> catchAuthAnnotationIsNowhereException(AuthAnnotationIsNowhereException e) {
        log.error(e.getMessage());
        return ResponseCustom.BAD_REQUEST(e.getMessage());
    }
}
