package smile.iceBulterrecipe.recipe.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import smile.iceBulterrecipe.global.resolver.Auth;
import smile.iceBulterrecipe.global.resolver.IsLogin;
import smile.iceBulterrecipe.global.resolver.LoginStatus;
import smile.iceBulterrecipe.global.response.ResponseCustom;
import smile.iceBulterrecipe.recipe.service.RecipeServiceImpl;
import smile.iceBulterrecipe.user.exception.UserNotFoundException;

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

    // 레시피 상세

    // 레시피 신고

    // 레시피 추가

    // 레시피 검색

    // 레시피 즐겨찾기

    // 레시피 즐겨찾기 모음

    // 마이 레시피 조회

    // 마이 레시피 삭제

    // 마이 레시피 수정
}
