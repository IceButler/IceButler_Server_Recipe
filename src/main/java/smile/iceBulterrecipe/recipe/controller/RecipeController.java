package smile.iceBulterrecipe.recipe.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import smile.iceBulterrecipe.global.feign.dto.response.RecipeFridgeFoodListsRes;
import smile.iceBulterrecipe.global.feign.feignClient.MainServerClient;
import smile.iceBulterrecipe.global.resolver.Auth;
import smile.iceBulterrecipe.global.resolver.IsLogin;
import smile.iceBulterrecipe.global.resolver.LoginStatus;
import smile.iceBulterrecipe.global.response.ResponseCustom;
import smile.iceBulterrecipe.recipe.service.RecipeServiceImpl;

@RequestMapping("/recipes")
@RestController
@RequiredArgsConstructor
public class RecipeController {

    private final RecipeServiceImpl recipeService;
    private final MainServerClient mainServerClient;

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
                                                @RequestParam(name = "fridgeIdx", required = false) Long fridgeIdx,
                                                @RequestParam(name = "multiFridgeIdx", required = false) Long multiFridgeIdx){
        if(fridgeIdx != null && multiFridgeIdx == null){
            return ResponseCustom.OK(this.recipeService.getFridgeRecipeLists(loginStatus.getUserIdx(), fridgeIdx));
        }else if(fridgeIdx == null && multiFridgeIdx != null){
            return ResponseCustom.OK(this.recipeService.getMultiFridgeRecipeLists(loginStatus.getUserIdx(), multiFridgeIdx));
        }else {
            return ResponseCustom.OK(this.recipeService.getPopularRecipeLists(loginStatus.getUserIdx()));
        }
    }

    @Auth
    @GetMapping("{fridgeIdx}/bookmark")
    public ResponseCustom<?> getBookmarkRecipes(@PathVariable Long fridgeIdx,
                                                @IsLogin LoginStatus loginStatus) {
        Long userIdx = loginStatus.getUserIdx();
        ResponseCustom<RecipeFridgeFoodListsRes> fridgeFoodLists = mainServerClient.getFridgeFoodLists(fridgeIdx, null, userIdx);
        if(fridgeFoodLists.getData()==null) return fridgeFoodLists;
        return ResponseCustom.OK(this.recipeService.getBookmarkRecipes(userIdx, fridgeFoodLists.getData()));
    }

    // 레시피 즐겨찾기
    @Auth
    @PostMapping("{recipeIdx}/bookmark")
    public ResponseCustom<?> bookmarkRecipe(@PathVariable Long recipeIdx,
                                            @IsLogin LoginStatus loginStatus) {
        this.recipeService.bookmarkRecipe(recipeIdx, loginStatus.getUserIdx());
        return ResponseCustom.OK();
    }
}
