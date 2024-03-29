package smile.iceBulterrecipe.recipe.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import smile.iceBulterrecipe.food.dto.assembler.FoodAssembler;
import smile.iceBulterrecipe.food.entity.Food;
import smile.iceBulterrecipe.food.exception.ChatGPTErrorException;
import smile.iceBulterrecipe.food.repository.FoodRepository;
import smile.iceBulterrecipe.food.service.FoodServiceImpl;
import smile.iceBulterrecipe.global.feign.dto.response.RecipeFridgeFoodListsRes;
import smile.iceBulterrecipe.global.sqs.AmazonSQSSender;
import smile.iceBulterrecipe.global.sqs.FoodData;
import smile.iceBulterrecipe.recipe.Reason;
import smile.iceBulterrecipe.recipe.dto.assembler.CookeryAssembler;
import smile.iceBulterrecipe.recipe.dto.assembler.RecipeAssembler;
import smile.iceBulterrecipe.recipe.dto.assembler.RecipeFoodAssembler;
import smile.iceBulterrecipe.recipe.dto.assembler.RecipeLikeAssembler;
import smile.iceBulterrecipe.recipe.dto.request.PostRecipeReq;
import smile.iceBulterrecipe.recipe.dto.response.BookmarkRes;
import smile.iceBulterrecipe.recipe.dto.response.MyRecipeRes;
import smile.iceBulterrecipe.recipe.dto.response.RecipeDetailsRes;
import smile.iceBulterrecipe.recipe.dto.response.RecipeRes;
import smile.iceBulterrecipe.recipe.entity.*;
import smile.iceBulterrecipe.recipe.exception.InvalidRecipeUserException;
import smile.iceBulterrecipe.recipe.exception.RecipeNotFoundException;
import smile.iceBulterrecipe.recipe.repository.CookeryRepository;
import smile.iceBulterrecipe.recipe.repository.RecipeReportRepository;
import smile.iceBulterrecipe.recipe.repository.recipe.RecipeRepository;
import smile.iceBulterrecipe.recipe.repository.recipeFood.RecipeFoodRepository;
import smile.iceBulterrecipe.recipe.repository.recipeLike.RecipeLikeRepository;
import smile.iceBulterrecipe.user.entity.User;
import smile.iceBulterrecipe.user.exception.UserNotFoundException;
import smile.iceBulterrecipe.user.repository.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;

@RequiredArgsConstructor
@Service
public class RecipeServiceImpl implements RecipeService{
    private final UserRepository userRepository;
    private final RecipeLikeRepository recipeLikeRepository;
    private final RecipeRepository recipeRepository;
    private final RecipeFoodRepository recipeFoodRepository;
    private final CookeryRepository cookeryRepository;
    private final FoodRepository foodRepository;
    private final FoodServiceImpl foodService;

    private final RecipeLikeAssembler recipeLikeAssembler;
    private final FoodAssembler foodAssembler;
    private final RecipeAssembler recipeAssembler;
    private final RecipeFoodAssembler recipeFoodAssembler;
    private final CookeryAssembler cookeryAssembler;
    private final RecipeReportRepository recipeReportRepository;

    private final AmazonSQSSender amazonSQSSender;

    // 인기 레시피
    @Override
    public Page<RecipeRes> getPopularRecipeListsForFridge(Long userIdx, RecipeFridgeFoodListsRes fridgeFoodList, Pageable pageable) {
        User user = this.userRepository.findByUserIdxAndIsEnable(userIdx, true).orElseThrow(UserNotFoundException::new);
        List<UUID> foodIdxes = this.foodAssembler.toFoodIdxes(fridgeFoodList);
        Page<RecipeRes> recipeList = this.recipeLikeRepository.getPopularRecipe(pageable);

        recipeList.toList()
                .forEach(r -> {
                    r.setRecipeLikeStatus(this.recipeLikeRepository.existsByUserAndRecipe_RecipeIdxAndIsEnable(user, r.getRecipeIdx(), true));
                    r.setPercentageOfFood(this.recipeFoodRepository.getPercentageOfFood(r.getRecipeIdx(), foodIdxes));
                });
        return recipeList;
    }

    // 냉장고 레시피
    @Override
    public Page<RecipeRes> getFridgeRecipeLists(Long userIdx, RecipeFridgeFoodListsRes fridgeFoodList, Pageable pageable) {
        User user = this.userRepository.findByUserIdxAndIsEnable(userIdx, true).orElseThrow(UserNotFoundException::new);
        List<UUID> foodIdxes = this.foodAssembler.toFoodIdxes(fridgeFoodList);
        Page<RecipeRes> recipeList = this.recipeFoodRepository.getRecipeByFridgeFoodList(pageable, foodIdxes);
        recipeList.toList()
                .forEach(r -> {
                    r.setRecipeLikeStatus(this.recipeLikeRepository.existsByUserAndRecipe_RecipeIdxAndIsEnable(user, r.getRecipeIdx(), true));
                    r.setPercentageOfFood(this.recipeFoodRepository.getPercentageOfFood(r.getRecipeIdx(), foodIdxes));
                });
        return recipeList;
    }

    // 레시피 즐겨찾기 모음
    @Override
    public Page<RecipeRes> getBookmarkRecipes(Long userIdx, RecipeFridgeFoodListsRes fridgeFoodList, Pageable pageable) {
        User user = this.userRepository.findByUserIdxAndIsEnable(userIdx, true).orElseThrow(UserNotFoundException::new);
        List<UUID> foodIdxes = this.foodAssembler.toFoodIdxes(fridgeFoodList);
        List<RecipeLike> bookmarkRecipeList = this.recipeLikeRepository.getBookmarkRecipe(user, true);
        return this.recipeFoodRepository.getBookmarkRecipes(bookmarkRecipeList, foodIdxes, pageable);
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

    public RecipeDetailsRes getRecipeDetails(Long recipeIdx, Long userIdx) {
        User user = this.userRepository.findByUserIdxAndIsEnable(userIdx, true).orElseThrow(UserNotFoundException::new);
        Recipe recipe = this.recipeRepository.findByRecipeIdxAndIsEnable(recipeIdx, true).orElseThrow(RecipeNotFoundException::new);
        List<RecipeFood> recipeFoods = this.recipeFoodRepository.findByRecipeAndIsEnable(recipe, true);
//        List<Cookery> cookery = this.cookeryRepository.findByRecipeAndIsEnable(recipe, true);
        List<Cookery> cookery = this.cookeryRepository.findByRecipeAndIsEnableOrderByNextIdx(recipe, true);
        Boolean isSubscribe = this.recipeLikeRepository.findByUserAndRecipeAndIsEnable(user, recipe, true).isPresent();
        return RecipeDetailsRes.toDto(recipe, recipeFoods, cookery, isSubscribe);
    }

    @Transactional
    @Override
    public void postRecipe(PostRecipeReq recipeReq, Long userIdx) {
        User user = this.userRepository.findByUserIdxAndIsEnable(userIdx, true).orElseThrow(UserNotFoundException::new);

        Recipe recipe = this.recipeRepository.save(this.recipeAssembler.toEntity(recipeReq, user));
        recipeReq.getFoodList().forEach(food -> {
            Food foodEntity = this.foodRepository.findByFoodName(food.getFoodName())
                    .orElseGet(() -> {
                        String foodCategory = getFoodCategoryGPT(food.getFoodName());
                        Food saveFood = this.foodRepository.save(this.foodAssembler.toEntity(food, foodCategory));
                        amazonSQSSender.sendMessage(FoodData.toDto(saveFood));
                        return saveFood;
                    });
            this.recipeFoodRepository.save(this.recipeFoodAssembler.toEntity(food, foodEntity, recipe));
        });
        AtomicReference<Long> nextIdx = new AtomicReference<>(0L);
        recipeReq.getCookeryList().forEach(cookery -> {
            this.cookeryRepository.save(this.cookeryAssembler.toEntity(cookery, recipe, nextIdx.getAndSet(nextIdx.get() + 1))) ;
        });
    }

    //레시피 수정
    @Override
    @Transactional
    public void updateRecipe(PostRecipeReq recipeReq, Long userIdx, Long recipeIdx) {
        User user = this.userRepository.findByUserIdxAndIsEnable(userIdx, true).orElseThrow(UserNotFoundException::new);

        Recipe recipe = this.recipeRepository.findByRecipeIdxAndIsEnable(recipeIdx, true).orElseThrow(RecipeNotFoundException::new);

        if (!recipe.getUser().equals(user)) {
            throw new InvalidRecipeUserException();
        }
        recipe.updateRecipe(recipeReq.getRecipeName(),recipeReq.getRecipeImgKey(),recipeReq.getQuantity(),recipeReq.getLeadTime(),RecipeCategory.getFoodCategoryByName(recipeReq.getRecipeCategory()));

        this.recipeFoodRepository.deleteByRecipe(recipe);

        recipeReq.getFoodList().forEach(food -> {
            Food foodEntity = this.foodRepository.findByFoodName(food.getFoodName())
                    .orElseGet(() -> {
                        String foodCategory = getFoodCategoryGPT(food.getFoodName());
                        Food saveFood = this.foodRepository.save(this.foodAssembler.toEntity(food, foodCategory));
                        return saveFood;
                    });
            this.recipeFoodRepository.save(this.recipeFoodAssembler.toEntity(food, foodEntity, recipe));
        });

        this.cookeryRepository.deleteByRecipe(recipe);

        AtomicReference<Long> nextIdx = new AtomicReference<>(0L);
        recipeReq.getCookeryList().forEach(cookery -> {
            this.cookeryRepository.save(this.cookeryAssembler.toEntity(cookery, recipe, nextIdx.getAndSet(nextIdx.get() + 1)));
        });
    }

    // 마이 레시피 기본 정보 조회 api
    @Override
    public RecipeRes getMyRecipeBasicInfo(Long recipeIdx, Long userIdx) {
        User user = this.userRepository.findByUserIdxAndIsEnable(userIdx, true).orElseThrow(UserNotFoundException::new);
        Recipe recipe = this.recipeRepository.findByRecipeIdxAndIsEnable(recipeIdx, true).orElseThrow(RecipeNotFoundException::new);
        if (!recipe.getUser().equals(user)) throw new InvalidRecipeUserException();
        return RecipeRes.toDto(recipe);
    }

    // 레시피 기본 정보 조회 api
    @Override
    public RecipeRes getRecipeBasicInfo(Long recipeIdx, RecipeFridgeFoodListsRes foodLists, Long userIdx) {
        User user = this.userRepository.findByUserIdxAndIsEnable(userIdx, true).orElseThrow(UserNotFoundException::new);
        Recipe recipe = this.recipeRepository.findByRecipeIdxAndIsEnable(recipeIdx, true).orElseThrow(RecipeNotFoundException::new);
        List<UUID> foodIdxes = this.foodAssembler.toFoodIdxes(foodLists);
        return RecipeRes.toDto(recipe,
                this.recipeFoodRepository.getPercentageOfFood(recipe.getRecipeIdx(), foodIdxes),
                this.recipeLikeRepository.existsByUserAndRecipe_RecipeIdxAndIsEnable(user, recipe.getRecipeIdx(), true));
    }

    private String getFoodCategoryGPT(String foodName){
        String foodCategory = null;
        try {
            foodCategory = this.foodService.callGPTCategory(foodName);
        } catch (Exception e){
            throw new ChatGPTErrorException();
        }
        return foodCategory;
    }

    // 마이 레시피 조회
    @Override
    public Page<MyRecipeRes> getMyRecipe(Long userIdx, Pageable pageable) {
        User user = this.userRepository.findByUserIdxAndIsEnable(userIdx, true).orElseThrow(UserNotFoundException::new);
        Page<Recipe> myRecipeList = this.recipeRepository.findByUserAndIsEnable(user, true, pageable);
        return this.recipeAssembler.toDtoMyRecipeList(myRecipeList);
    }

    // 레시피 검색(레시피)
    @Override
    public Page<RecipeRes> getSearchRecipeListForRecipe(Long userIdx, RecipeFridgeFoodListsRes foodLists, String keyword, Pageable pageable) {
        User user = this.userRepository.findByUserIdxAndIsEnable(userIdx, true).orElseThrow(UserNotFoundException::new);
        return getSearchRecipeMethods(user, this.recipeRepository.findByRecipeNameContainingAndIsEnable(keyword, true, pageable), foodLists);
    }

    private Page<RecipeRes> getSearchRecipeMethods(User user, Page<Recipe> recipeLists, RecipeFridgeFoodListsRes foodLists) {
        List<UUID> foodIdxes = this.foodAssembler.toFoodIdxes(foodLists);
        Page<RecipeRes> recipeRes = this.recipeAssembler.toDtoList(recipeLists);
        recipeRes.toList().forEach(r ->
                {
                    r.setRecipeLikeStatus(this.recipeLikeRepository.existsByUserAndRecipe_RecipeIdxAndIsEnable(user, r.getRecipeIdx(), true));
                    r.setPercentageOfFood(this.recipeFoodRepository.getPercentageOfFood(r.getRecipeIdx(), foodIdxes));
                });
        return recipeRes;
    }
    // 레시피 검색(음식)
    @Override
    public Page<RecipeRes> getSearchRecipeListForFood(Long userIdx, RecipeFridgeFoodListsRes foodLists, String keyword, Pageable pageable) {
        User user = this.userRepository.findByUserIdxAndIsEnable(userIdx, true).orElseThrow(UserNotFoundException::new);
        return getSearchRecipeMethods(user, this.recipeRepository.findByFoodNameContainingAndIsEnableHavingRecipe(keyword, pageable), foodLists);
    }
    // recipe report
    @Override
    public void reportRecipe(Long recipeIdx, Long userIdx, String reason) {
        User user = this.userRepository.findByUserIdxAndIsEnable(userIdx, true).orElseThrow(UserNotFoundException::new);
        Reason recipeReason = Reason.getFoodCategoryByName(reason);
        Recipe recipe = this.recipeRepository.findByRecipeIdxAndIsEnable(recipeIdx, true).orElseThrow(RecipeNotFoundException::new);

        this.recipeReportRepository.save(this.recipeAssembler.toReportEntity(recipe, user, recipeReason));
    }
    // 마이 레시피 삭제
    @Override
    public void deleteMyRecipe(Long recipeIdx, Long userIdx) {
        User user = this.userRepository.findByUserIdxAndIsEnable(userIdx, true).orElseThrow(UserNotFoundException::new);
        Recipe recipe = this.recipeRepository.findByRecipeIdxAndIsEnable(recipeIdx, true).orElseThrow(RecipeNotFoundException::new);
        if(!recipe.getUser().equals(user)) throw new InvalidRecipeUserException();
        this.recipeRepository.deleteById(recipe.getRecipeIdx());
    }

}
