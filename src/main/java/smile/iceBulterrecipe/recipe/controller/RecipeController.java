package smile.iceBulterrecipe.recipe.controller;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import smile.iceBulterrecipe.global.resolver.Auth;
import smile.iceBulterrecipe.global.resolver.IsLogin;
import smile.iceBulterrecipe.global.resolver.LoginStatus;
import smile.iceBulterrecipe.global.response.ResponseCustom;
import smile.iceBulterrecipe.recipe.service.RecipeService;
import smile.iceBulterrecipe.recipe.service.RecipeServiceImpl;

@RequestMapping("/recipes")
@RestController
@RequiredArgsConstructor
public class RecipeController {

    private final RecipeServiceImpl recipeService;

    @GetMapping("/health")
    public String checkHealth() {
        return "healthy";
    }

    /**
     * 메인화면 냉장고 속 레시피 / 인기 레시피
     */
    @Auth
    @GetMapping("")
    public ResponseCustom<?> getRecipeMainLists(@IsLogin LoginStatus loginStatus,
                                                @RequestParam(name = "category") String recipeListCategory){
        return ResponseCustom.OK(this.recipeService.getRecipeMainLists(loginStatus.getUserIdx(), recipeListCategory));
    }
}
