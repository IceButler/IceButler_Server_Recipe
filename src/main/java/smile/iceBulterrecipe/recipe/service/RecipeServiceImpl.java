package smile.iceBulterrecipe.recipe.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import smile.iceBulterrecipe.global.feign.dto.response.RecipeFridgeFoodListRes;
import smile.iceBulterrecipe.global.feign.dto.response.RecipeFridgeFoodListsRes;
import smile.iceBulterrecipe.recipe.dto.assembler.RecipeAssembler;
import smile.iceBulterrecipe.recipe.dto.assembler.RecipeLikeAssembler;
import smile.iceBulterrecipe.recipe.dto.response.BookMarkRecipeListRes;
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
    private final RecipeLikeAssembler recipeLikeAssembler;

    // 인기 레시피
    @Override
    public RecipeMainListRes getPopularRecipeLists(Long userIdx) {
        User user = this.userRepository.findByUserIdxAndIsEnable(userIdx, true).orElseThrow(UserNotFoundException::new);
        List<RecipeMainRes> recipe = this.recipeAssembler.toBasicMainDTO(this.recipeLikeRepository.getPopularRecipe());
        for (RecipeMainRes res : recipe) {
            this.recipeAssembler.toUserLikeStatus(res, this.recipeLikeRepository.existsByUserAndRecipe_RecipeIdxAndIsEnable(user, res.getRecipeIdx(), true));
        }
        // todo: 퍼센트 이전
        return RecipeMainListRes.toDto(recipe);
    }

    // 냉장고 레시피
    @Override
    public RecipeMainListRes getFridgeRecipeLists(Long userIdx, Long fridgeIdx) {
        User user = this.userRepository.findByUserIdxAndIsEnable(userIdx, true).orElseThrow(UserNotFoundException::new);
        // food 리스트를 받아서
        // foodList 객체를 생성한 다음
        // 레시피 푸드 중에서 foodList의 값을 많이 갖고 잇는 레시피 순으로 불러오고 그 갯수의 결과 값을 return
        return null;
    }

    // 멀티 냉장고 레시피
    @Override
    public RecipeMainListRes getMultiFridgeRecipeLists(Long userIdx, Long multiFridgeIdx) {
        User user = this.userRepository.findByUserIdxAndIsEnable(userIdx, true).orElseThrow(UserNotFoundException::new);
        return null;
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
