package smile.iceBulterrecipe.recipe.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import smile.iceBulterrecipe.food.dto.assembler.FoodAssembler;
import smile.iceBulterrecipe.food.entity.Food;
import smile.iceBulterrecipe.food.repository.FoodRepository;
import smile.iceBulterrecipe.global.feign.dto.response.RecipeFridgeFoodListRes;
import smile.iceBulterrecipe.global.feign.dto.response.RecipeFridgeFoodListsRes;
import smile.iceBulterrecipe.recipe.dto.assembler.RecipeAssembler;
import smile.iceBulterrecipe.recipe.dto.assembler.RecipeLikeAssembler;
import smile.iceBulterrecipe.recipe.dto.response.BookMarkRecipeListRes;
import smile.iceBulterrecipe.recipe.dto.response.BookmarkRecipeRes;
import smile.iceBulterrecipe.recipe.dto.response.RecipeMainListRes;
import smile.iceBulterrecipe.recipe.dto.response.RecipeMainRes;
import smile.iceBulterrecipe.recipe.entity.Recipe;
import smile.iceBulterrecipe.recipe.entity.RecipeLike;
import smile.iceBulterrecipe.recipe.exception.RecipeNotFoundException;
import smile.iceBulterrecipe.recipe.repository.RecipeRepository;
import smile.iceBulterrecipe.recipe.repository.recipeFood.RecipeFoodRepository;
import smile.iceBulterrecipe.recipe.repository.recipeLike.RecipeLikeRepository;
import smile.iceBulterrecipe.user.entity.User;
import smile.iceBulterrecipe.user.exception.UserNotFoundException;
import smile.iceBulterrecipe.user.repository.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class RecipeServiceImpl implements RecipeService{
    private final UserRepository userRepository;
    private final RecipeLikeRepository recipeLikeRepository;
    private final RecipeRepository recipeRepository;
    private final RecipeAssembler recipeAssembler;
    private final RecipeFoodRepository recipeFoodRepository;
    private final FoodRepository foodRepository;
    private final FoodAssembler foodAssembler;
    private final RecipeLikeAssembler recipeLikeAssembler;

    // 인기 레시피
    @Override
    public RecipeMainListRes getPopularRecipeListsForFridge(Long userIdx, RecipeFridgeFoodListsRes fridgeFoodList) {
        User user = this.userRepository.findByUserIdxAndIsEnable(userIdx, true).orElseThrow(UserNotFoundException::new);
        List<Long> foodIdxes = this.foodAssembler.toFoodIdxes(fridgeFoodList);
        List<Recipe> recipeList = this.recipeLikeRepository.getPopularRecipe();

        return new RecipeMainListRes(recipeList.stream()
                .map(r -> RecipeMainRes.toDto(r, this.recipeFoodRepository.getPercentageOfFood(r, foodIdxes),
                        this.recipeLikeRepository.existsByUserAndRecipe_RecipeIdxAndIsEnable(user, r.getRecipeIdx(), true)))
                .collect(Collectors.toList()));
    }

    // 냉장고 레시피
    @Override
    public RecipeMainListRes getFridgeRecipeLists(Long userIdx, RecipeFridgeFoodListsRes fridgeFoodList) {
        User user = this.userRepository.findByUserIdxAndIsEnable(userIdx, true).orElseThrow(UserNotFoundException::new);
        List<Long> foodIdxes = this.foodAssembler.toFoodIdxes(fridgeFoodList);
        List<Recipe> recipeList = this.recipeFoodRepository.getRecipeByFridgeFoodList(foodIdxes);

        return new RecipeMainListRes(recipeList.stream()
                .map(r -> RecipeMainRes.toDto(r, this.recipeFoodRepository.getPercentageOfFood(r, foodIdxes),
                        this.recipeLikeRepository.existsByUserAndRecipe_RecipeIdxAndIsEnable(user, r.getRecipeIdx(), true)))
                .collect(Collectors.toList()));
    }

    // 레시피 즐겨찾기 모음
    @Override
    public BookMarkRecipeListRes getBookmarkRecipes(Long userIdx, RecipeFridgeFoodListsRes fridgeFoodList) {
        User user = this.userRepository.findByUserIdxAndIsEnable(userIdx, true).orElseThrow(UserNotFoundException::new);
        List<Long> foodIdxes = fridgeFoodList.getFoodList().stream()
                .map(RecipeFridgeFoodListRes::getFoodIdx)
                .collect(Collectors.toList());
        List<Recipe> bookmarkRecipeList = this.recipeLikeRepository.getBookmarkRecipe(user, true);
        return this.recipeFoodRepository.getBookmarkRecipes(bookmarkRecipeList, foodIdxes);
    }

    // 레시피 즐겨찾기
    @Transactional
    @Override
    public void bookmarkRecipe(Long recipeIdx, Long userIdx) {
        User user = this.userRepository.findByUserIdxAndIsEnable(userIdx, true).orElseThrow(UserNotFoundException::new);
        Recipe recipe = this.recipeRepository.findByRecipeIdxAndIsEnable(recipeIdx, true).orElseThrow(RecipeNotFoundException::new);
        Optional<RecipeLike> optionalRecipeLike = this.recipeLikeRepository.findByUserAndRecipe(user, recipe);
        // RecipeLike가 있다면 isEnable 변겅, 없다면 생성
        if(optionalRecipeLike.isPresent()) {
            RecipeLike recipeLike = optionalRecipeLike.get();
            recipeLike.setIsEnable(!recipeLike.getIsEnable());
        } else {
            RecipeLike recipeLike = recipeLikeAssembler.toEntity(user, recipe);
            recipeLike.setIsEnable(true);
            this.recipeLikeRepository.save(recipeLike);
        }
    }


}
