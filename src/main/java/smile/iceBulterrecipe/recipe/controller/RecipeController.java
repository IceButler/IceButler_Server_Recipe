package smile.iceBulterrecipe.recipe.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import smile.iceBulterrecipe.global.Constant;
import smile.iceBulterrecipe.global.feign.dto.response.RecipeFridgeFoodListsRes;
import smile.iceBulterrecipe.global.feign.feignClient.MainServerClient;
import smile.iceBulterrecipe.global.resolver.Auth;
import smile.iceBulterrecipe.global.resolver.IsLogin;
import smile.iceBulterrecipe.global.resolver.LoginStatus;
import smile.iceBulterrecipe.global.response.ResponseCustom;
import smile.iceBulterrecipe.recipe.dto.request.PostRecipeReportReq;
import smile.iceBulterrecipe.recipe.dto.request.PostRecipeReq;
import smile.iceBulterrecipe.recipe.dto.response.BookmarkRes;
import smile.iceBulterrecipe.recipe.dto.response.MyRecipeRes;
import smile.iceBulterrecipe.recipe.dto.response.RecipeDetailsRes;
import smile.iceBulterrecipe.recipe.exception.RecipeListCategoryNotFoundException;
import smile.iceBulterrecipe.recipe.service.RecipeServiceImpl;

import org.springframework.data.domain.Pageable;
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
    @GetMapping("/{fridgeIdx}") // uel?page=0&size=10
    public ResponseCustom<?> getRecipeMainLists(@IsLogin LoginStatus loginStatus,
                                                @PathVariable(name = "fridgeIdx") Long fridgeIdx,
                                                @RequestParam(name = "category") String category,
                                                Pageable pageable){
        ResponseCustom<RecipeFridgeFoodListsRes> lists = this.mainServerClient.getFridgeFoodLists(fridgeIdx, null, loginStatus.getUserIdx());
        if(lists.getData() == null) return lists;

        if(category.equals(Constant.RecipeConstant.FRIDGE_FOOD_RECIPE)){
            return ResponseCustom.OK(this.recipeService.getFridgeRecipeLists(loginStatus.getUserIdx(), lists.getData(), pageable));
        }else if(category.equals(Constant.RecipeConstant.POPULAR_FOOD)){
            return ResponseCustom.OK(this.recipeService.getPopularRecipeListsForFridge(loginStatus.getUserIdx(),  lists.getData(), pageable));
        }else{
            throw new RecipeListCategoryNotFoundException();
        }
    }

    // 가정용 냉장고 레시피 즐겨찾기 모음
    @Auth
    @GetMapping("{fridgeIdx}/bookmark")
    public ResponseCustom<?> getBookmarkRecipes(@PathVariable Long fridgeIdx,
                                                @IsLogin LoginStatus loginStatus,
                                                Pageable pageable) {
        Long userIdx = loginStatus.getUserIdx();
        ResponseCustom<RecipeFridgeFoodListsRes> fridgeFoodLists = mainServerClient.getFridgeFoodLists(fridgeIdx, null, userIdx);
        if(fridgeFoodLists.getData()==null) return fridgeFoodLists;
        return ResponseCustom.OK(this.recipeService.getBookmarkRecipes(userIdx, fridgeFoodLists.getData(), pageable));
    }

    // 레시피 즐겨찾기
    @Auth
    @PostMapping("{recipeIdx}/bookmark")
    public ResponseCustom<BookmarkRes> bookmarkRecipe(@PathVariable Long recipeIdx,
                                                      @IsLogin LoginStatus loginStatus) {
        return ResponseCustom.OK(this.recipeService.bookmarkRecipe(recipeIdx, loginStatus.getUserIdx()));
    }

    // 레시피 상세조회
    @Auth
    @GetMapping("/detail/{recipeIdx}")
    public ResponseCustom<RecipeDetailsRes> getRecipeDetails(@PathVariable Long recipeIdx,
                                                             @IsLogin LoginStatus loginStatus) {
        return ResponseCustom.OK(this.recipeService.getRecipeDetails(recipeIdx));
    }
    
    // 레시피 추가
    @Auth
    @PostMapping("")
    public ResponseCustom<?> postRecipe(@IsLogin LoginStatus loginStatus, @RequestBody PostRecipeReq recipeReq){
        this.recipeService.postRecipe(recipeReq, loginStatus.getUserIdx());
        return ResponseCustom.OK();
    }

    //레시피 수정
    @Auth
    @PatchMapping("{recipeIdx}/modify")
    public ResponseCustom<Void> updateRecipe(@IsLogin LoginStatus loginStatus
            , @RequestBody PostRecipeReq recipeReq
            , @PathVariable Long recipeIdx
    ){
        this.recipeService.updateRecipe(recipeReq, loginStatus.getUserIdx(),recipeIdx);
        return ResponseCustom.OK();
    }

    // 마이 레시피 조회
    @Auth
    @GetMapping("/myrecipe")
    public ResponseCustom<Page<MyRecipeRes>> getMyRecipe(@IsLogin LoginStatus loginStatus,
                                                         Pageable pageable) {
        return ResponseCustom.OK(this.recipeService.getMyRecipe(loginStatus.getUserIdx(), pageable));
    }

    // 레시피 검색
    @Auth
    @GetMapping("/search")
    public ResponseCustom<?> getSearchRecipeList(@IsLogin LoginStatus loginStatus,
                                                 @RequestParam(value = "keyword") String keyword,
                                                 @RequestParam(name = "category") String category,
                                                 Pageable pageable){
        if(category.equals(Constant.RecipeConstant.SEARCH_RECIPE)){
            return ResponseCustom.OK(this.recipeService.getSearchRecipeListForRecipe(loginStatus.getUserIdx(), keyword, pageable));
        }else if(category.equals(Constant.RecipeConstant.SEARCH_FOOD)){
            return ResponseCustom.OK(this.recipeService.getSearchRecipeListForFood(loginStatus.getUserIdx(), keyword, pageable));
        }else {
            throw new RecipeListCategoryNotFoundException();
        }

        }

    // 마이 레시피 삭제
    @Auth
    @DeleteMapping("/{recipeIdx}/myrecipe")
    public ResponseCustom<?> deleteMyRecipe(@PathVariable Long recipeIdx,
                                            @IsLogin LoginStatus loginStatus) {
        this.recipeService.deleteMyRecipe(recipeIdx, loginStatus.getUserIdx());
        return ResponseCustom.OK();
    }

    // 레시피 신고
    @Auth
    @PostMapping("/{recipeIdx}/report")
    public ResponseCustom<?> reportRecipe(@PathVariable Long recipeIdx,
                                          @IsLogin LoginStatus loginStatus,
                                          @RequestBody PostRecipeReportReq reportReq) {
        this.recipeService.reportRecipe(recipeIdx, loginStatus.getUserIdx(), reportReq.getReason());
        return ResponseCustom.OK();
    }

}



