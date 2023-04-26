package smile.iceBulterrecipe.recipe.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import smile.iceBulterrecipe.global.Constant;
import smile.iceBulterrecipe.global.feign.dto.response.RecipeFridgeFoodListsRes;
import smile.iceBulterrecipe.global.feign.feignClient.MainServerClient;
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
    private final MainServerClient mainServerClient;

    @GetMapping("/health")
    public String checkHealth() {
        return "healthy";
    }

    /**
     * 메인화면 냉장고 속 레시피 / 인기 레시피
     */
    @Auth
    @GetMapping("/{fridgeIdx}")
    public ResponseCustom<?> getRecipeMainLists(@IsLogin LoginStatus loginStatus,
                                                @PathVariable(name = "fridgeIdx") Long fridgeIdx,
                                                @RequestParam(name = "category") String category){
        ResponseCustom<RecipeFridgeFoodListsRes> lists = this.mainServerClient.getFridgeFoodLists(fridgeIdx, null, loginStatus.getUserIdx());
        if(lists.getData() == null) return lists;

        if(category.equals(Constant.RecipeConstant.FRIDGE_FOOD_RECIPE)){
            return ResponseCustom.OK(this.recipeService.getFridgeRecipeLists(loginStatus.getUserIdx(), fridgeIdx, lists.getData()));
        }else if(category.equals(Constant.RecipeConstant.POPULAR_FOOD)){
            return ResponseCustom.OK(this.recipeService.getPopularRecipeListsForFridge(loginStatus.getUserIdx(),  lists.getData()));
        }else{
            throw new UserNotFoundException(); // todo : 에러 고치기 ;0;
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
}
