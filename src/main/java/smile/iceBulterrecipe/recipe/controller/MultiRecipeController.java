package smile.iceBulterrecipe.recipe.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import smile.iceBulterrecipe.global.Constant;
import smile.iceBulterrecipe.global.feign.dto.response.RecipeFridgeFoodListRes;
import smile.iceBulterrecipe.global.feign.dto.response.RecipeFridgeFoodListsRes;
import smile.iceBulterrecipe.global.feign.feignClient.MainServerClient;
import smile.iceBulterrecipe.global.resolver.Auth;
import smile.iceBulterrecipe.global.resolver.IsLogin;
import smile.iceBulterrecipe.global.resolver.LoginStatus;
import smile.iceBulterrecipe.global.response.ResponseCustom;
import smile.iceBulterrecipe.recipe.exception.RecipeListCategoryNotFoundException;
import smile.iceBulterrecipe.recipe.service.RecipeServiceImpl;
import smile.iceBulterrecipe.user.exception.UserNotFoundException;
import org.springframework.data.domain.Pageable;

@RequestMapping("/multiRecipes")
@RestController
@RequiredArgsConstructor
public class MultiRecipeController {

    private final RecipeServiceImpl recipeService;
    private final MainServerClient mainServerClient;

    // 레시피 메인 화면
    @Auth
    @GetMapping("/{multiFridgeIdx}")
    public ResponseCustom<?> getRecipeMainLists(@IsLogin LoginStatus loginStatus,
                                                @PathVariable(name = "multiFridgeIdx") Long multiFridgeIdx,
                                                @RequestParam(name = "category") String category,
                                                Pageable pageable){
        ResponseCustom<RecipeFridgeFoodListsRes> lists = this.mainServerClient.getFridgeFoodLists(null, multiFridgeIdx, loginStatus.getUserIdx());
        if(lists.getData() == null) return lists;
        if(category.equals(Constant.RecipeConstant.FRIDGE_FOOD_RECIPE)){
            return ResponseCustom.OK(this.recipeService.getFridgeRecipeLists(loginStatus.getUserIdx(),lists.getData(), pageable));
        }else if(category.equals(Constant.RecipeConstant.POPULAR_FOOD)){
            return ResponseCustom.OK(this.recipeService.getPopularRecipeListsForFridge(loginStatus.getUserIdx(),  lists.getData(), pageable));
        }else{
            throw  new RecipeListCategoryNotFoundException();
        }
    }
    // 레시피 즐겨찾기 모음
    @Auth
    @GetMapping("{multiFridgeIdx}/bookmark")
    public ResponseCustom<?> getBookmarkRecipes(@PathVariable Long multiFridgeIdx,
                                                @IsLogin LoginStatus loginStatus,
                                                Pageable pageable) {
        Long userIdx = loginStatus.getUserIdx();
        ResponseCustom<RecipeFridgeFoodListsRes> fridgeFoodLists = mainServerClient.getFridgeFoodLists(null, multiFridgeIdx, userIdx);
        if(fridgeFoodLists.getData()==null) return fridgeFoodLists;
        return ResponseCustom.OK(this.recipeService.getBookmarkRecipes(userIdx, fridgeFoodLists.getData(), pageable));
    }

    // 레시피 검색
    @Auth
    @GetMapping("/search/{multiFridgeIdx}")
    public ResponseCustom<?> getSearchRecipeList(@IsLogin LoginStatus loginStatus,
                                                 @PathVariable(name = "multiFridgeIdx") Long multiFridgeIdx,
                                                 @RequestParam(value = "keyword") String keyword,
                                                 @RequestParam(name = "category") String category,
                                                 Pageable pageable) {

        ResponseCustom<RecipeFridgeFoodListsRes> lists = this.mainServerClient.getFridgeFoodLists(null, multiFridgeIdx, loginStatus.getUserIdx());
        if (lists.getData() == null) return lists;

        if (category.equals(Constant.RecipeConstant.SEARCH_RECIPE)) {
            return ResponseCustom.OK(this.recipeService.getSearchRecipeListForRecipe(loginStatus.getUserIdx(), lists.getData(), keyword, pageable));
        } else if (category.equals(Constant.RecipeConstant.SEARCH_FOOD)) {
            return ResponseCustom.OK(this.recipeService.getSearchRecipeListForFood(loginStatus.getUserIdx(), lists.getData(), keyword, pageable));
        } else {
            throw new RecipeListCategoryNotFoundException();
        }

    }
}
