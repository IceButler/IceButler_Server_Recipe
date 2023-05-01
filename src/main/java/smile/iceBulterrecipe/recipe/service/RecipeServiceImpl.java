package smile.iceBulterrecipe.recipe.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import smile.iceBulterrecipe.food.dto.assembler.FoodAssembler;
import smile.iceBulterrecipe.food.entity.Food;
import smile.iceBulterrecipe.food.repository.FoodRepository;
import smile.iceBulterrecipe.global.feign.dto.response.RecipeFridgeFoodListsRes;
import smile.iceBulterrecipe.recipe.dto.assembler.CookeryAssembler;
import smile.iceBulterrecipe.recipe.dto.assembler.RecipeAssembler;
import smile.iceBulterrecipe.recipe.dto.assembler.RecipeFoodAssembler;
import smile.iceBulterrecipe.recipe.dto.assembler.RecipeLikeAssembler;
import smile.iceBulterrecipe.recipe.dto.request.PostRecipeReq;
import smile.iceBulterrecipe.recipe.dto.response.RecipeListRes;
import smile.iceBulterrecipe.recipe.dto.response.RecipeRes;
import smile.iceBulterrecipe.recipe.dto.response.*;
import smile.iceBulterrecipe.recipe.entity.Recipe;
import smile.iceBulterrecipe.recipe.entity.RecipeLike;
import smile.iceBulterrecipe.recipe.exception.RecipeNotFoundException;
import smile.iceBulterrecipe.recipe.repository.CookeryRepository;
import smile.iceBulterrecipe.recipe.repository.RecipeRepository;
import smile.iceBulterrecipe.recipe.repository.recipeFood.RecipeFoodRepository;
import smile.iceBulterrecipe.recipe.repository.recipeLike.RecipeLikeRepository;
import smile.iceBulterrecipe.user.entity.User;
import smile.iceBulterrecipe.user.exception.UserNotFoundException;
import smile.iceBulterrecipe.user.repository.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class RecipeServiceImpl implements RecipeService{
    private final UserRepository userRepository;
    private final RecipeLikeRepository recipeLikeRepository;
    private final RecipeRepository recipeRepository;
    private final RecipeFoodRepository recipeFoodRepository;
    private final CookeryRepository cookeryRepository;
    private final FoodRepository foodRepository;

    private final RecipeLikeAssembler recipeLikeAssembler;
    private final FoodAssembler foodAssembler;
    private final RecipeAssembler recipeAssembler;
    private final RecipeFoodAssembler recipeFoodAssembler;
    private final CookeryAssembler cookeryAssembler;

    // 인기 레시피
    @Override
    public RecipeListRes getPopularRecipeListsForFridge(Long userIdx, RecipeFridgeFoodListsRes fridgeFoodList) {
        User user = this.userRepository.findByUserIdxAndIsEnable(userIdx, true).orElseThrow(UserNotFoundException::new);
        List<Long> foodIdxes = this.foodAssembler.toFoodIdxes(fridgeFoodList);
        List<Recipe> recipeList = this.recipeLikeRepository.getPopularRecipe();

        return new RecipeListRes(recipeList.stream()
                .map(r -> RecipeRes.toDto(r, this.recipeFoodRepository.getPercentageOfFood(r, foodIdxes),
                        this.recipeLikeRepository.existsByUserAndRecipe_RecipeIdxAndIsEnable(user, r.getRecipeIdx(), true)))
                .collect(Collectors.toList()));
    }

    // 냉장고 레시피
    @Override
    public RecipeListRes getFridgeRecipeLists(Long userIdx, RecipeFridgeFoodListsRes fridgeFoodList) {
        User user = this.userRepository.findByUserIdxAndIsEnable(userIdx, true).orElseThrow(UserNotFoundException::new);
        List<Long> foodIdxes = this.foodAssembler.toFoodIdxes(fridgeFoodList);
        List<Recipe> recipeList = this.recipeFoodRepository.getRecipeByFridgeFoodList(foodIdxes);

        return new RecipeListRes(recipeList.stream()
                .map(r -> RecipeRes.toDto(r, this.recipeFoodRepository.getPercentageOfFood(r, foodIdxes),
                        this.recipeLikeRepository.existsByUserAndRecipe_RecipeIdxAndIsEnable(user, r.getRecipeIdx(), true)))
                .collect(Collectors.toList()));
    }

    // 레시피 즐겨찾기 모음
    @Override
    public RecipeListRes getBookmarkRecipes(Long userIdx, RecipeFridgeFoodListsRes fridgeFoodList) {
        User user = this.userRepository.findByUserIdxAndIsEnable(userIdx, true).orElseThrow(UserNotFoundException::new);
        List<Long> foodIdxes = this.foodAssembler.toFoodIdxes(fridgeFoodList);
        List<Recipe> bookmarkRecipeList = this.recipeLikeRepository.getBookmarkRecipe(user, true);
        return this.recipeFoodRepository.getBookmarkRecipes(bookmarkRecipeList, foodIdxes);
    }

    // 레시피 즐겨찾기
    @Transactional
    @Override
    public BookmarkRes bookmarkRecipe(Long recipeIdx, Long userIdx) {
        User user = this.userRepository.findByUserIdxAndIsEnable(userIdx, true).orElseThrow(UserNotFoundException::new);
        Recipe recipe = this.recipeRepository.findByRecipeIdxAndIsEnable(recipeIdx, true).orElseThrow(RecipeNotFoundException::new);
        Optional<RecipeLike> optionalRecipeLike = this.recipeLikeRepository.findByUserAndRecipe(user, recipe);
        // RecipeLike가 있다면 isEnable 변겅, 없다면 생성
        RecipeLike recipeLike;
        if(optionalRecipeLike.isPresent()) {
            recipeLike = optionalRecipeLike.get();
            recipeLike.setIsEnable(!recipeLike.getIsEnable());
        } else {
            recipeLike = recipeLikeAssembler.toEntity(user, recipe);
            recipeLike.setIsEnable(true);
            this.recipeLikeRepository.save(recipeLike);
        }
        return BookmarkRes.toDto(recipeLike);
    }

    @Transactional
    @Override
    public void postRecipe(PostRecipeReq recipeReq, Long userIdx) {
        User user = this.userRepository.findByUserIdxAndIsEnable(userIdx, true).orElseThrow(UserNotFoundException::new);

        // todo: 사용자의 음식이 없다면? 그럼 새로 만들어야 함 -> queue 로 진행할 것으로 예상이 됨 *^^* 아직 메인에는 저장 안되는 로직임.
        Recipe recipe = this.recipeRepository.save(this.recipeAssembler.toEntity(recipeReq, user));
        recipeReq.getFoodList().forEach(food -> {
            Food foodEntity = this.foodRepository.findByFoodName(food.getFoodName()).orElseGet(() -> this.foodRepository.save(this.foodAssembler.toEntity(food)));
            this.recipeFoodRepository.save(this.recipeFoodAssembler.toEntity(food, foodEntity, recipe));
        });
        AtomicReference<Long> nextIdx = new AtomicReference<>(0L);
        recipeReq.getCookeryList().forEach(cookery -> {
            this.cookeryRepository.save(this.cookeryAssembler.toEntity(cookery, recipe, nextIdx.getAndSet(nextIdx.get() + 1))) ;
        });
    }
}
