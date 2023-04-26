package smile.iceBulterrecipe.recipe.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import smile.iceBulterrecipe.global.feign.dto.response.RecipeFridgeFoodListsRes;
import smile.iceBulterrecipe.global.feign.feignClient.MainServerClient;
import smile.iceBulterrecipe.global.resolver.Auth;
import smile.iceBulterrecipe.global.resolver.IsLogin;
import smile.iceBulterrecipe.global.resolver.LoginStatus;
import smile.iceBulterrecipe.global.response.ResponseCustom;
import smile.iceBulterrecipe.recipe.service.RecipeServiceImpl;

@RequestMapping("/multiRecipes")
@RestController
@RequiredArgsConstructor
public class MultiRecipeController {

    private final RecipeServiceImpl recipeService;
    private MainServerClient mainServerClient;

    // 레시피 메인 화면

    // 레시피 즐겨찾기 모음
    @Auth
    @GetMapping("{multiFridgeIdx}/bookmark")
    public ResponseCustom<?> getBookmarkRecipes(@PathVariable Long multiFridgeIdx,
                                                @IsLogin LoginStatus loginStatus) {
        Long userIdx = loginStatus.getUserIdx();
        ResponseCustom<RecipeFridgeFoodListsRes> fridgeFoodLists = mainServerClient.getFridgeFoodLists(null, multiFridgeIdx, userIdx);
        if(fridgeFoodLists.getData()==null) return fridgeFoodLists;
        return ResponseCustom.OK(this.recipeService.getBookmarkRecipes(userIdx, fridgeFoodLists.getData()));
    }
}
