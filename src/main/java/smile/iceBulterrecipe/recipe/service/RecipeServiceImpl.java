package smile.iceBulterrecipe.recipe.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import smile.iceBulterrecipe.global.Constant;
import smile.iceBulterrecipe.recipe.dto.assembler.RecipeAssembler;
import smile.iceBulterrecipe.recipe.dto.response.RecipeMainListRes;
import smile.iceBulterrecipe.recipe.dto.response.RecipeMainRes;
import smile.iceBulterrecipe.recipe.repository.RecipeRepository;
import smile.iceBulterrecipe.recipe.repository.recipeLike.RecipeLikeRepository;
import smile.iceBulterrecipe.user.entity.User;
import smile.iceBulterrecipe.user.exception.UserNotFoundException;
import smile.iceBulterrecipe.user.repository.UserRepository;

import java.util.List;

@RequiredArgsConstructor
@Service
public class RecipeServiceImpl implements RecipeService{
    private final UserRepository userRepository;
    private final RecipeLikeRepository recipeLikeRepository;
    private final RecipeRepository recipeRepository;
    private final RecipeAssembler recipeAssembler;

    // 레시피 메인
    @Override
    public RecipeMainListRes getRecipeMainLists(Long userIdx, String recipeList) {
        User user = this.userRepository.findByUserIdxAndIsEnable(userIdx, true).orElseThrow(UserNotFoundException::new);
        if(recipeList.equals(Constant.RecipeConstant.FRIDGE_FOOD_RECIPE)){
            // 일단 ,, 인기부터 하고 고민할래요 .. 허헣.....
            return null;
        }else if(recipeList.equals(Constant.RecipeConstant.POPULAR_FOOD)){
            List<RecipeMainRes> recipe = this.recipeAssembler.toBasicMainDTO(this.recipeLikeRepository.getPopularRecipe());
            for(RecipeMainRes res : recipe){
                this.recipeAssembler.toUserLikeStatus(res, this.recipeLikeRepository.existsByUserAndRecipe_RecipeIdxAndIsEnable(user, res.getRecipeIdx(), true));
            }

            // todo: 퍼센트 이전
            return RecipeMainListRes.toDto(recipe);
        }else{
            throw new UserNotFoundException(); // todo: 에러 처리 다시 해야 함.
        }
    }
}
