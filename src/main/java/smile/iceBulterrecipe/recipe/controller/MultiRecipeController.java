package smile.iceBulterrecipe.recipe.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import smile.iceBulterrecipe.recipe.service.RecipeServiceImpl;

@RequestMapping("/multiRecipes")
@RestController
@RequiredArgsConstructor
public class MultiRecipeController {

    private final RecipeServiceImpl recipeService;

    // 레시피 메인 화면

    // 레시피 즐겨찾기 모음

}
