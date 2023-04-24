package smile.iceBulterrecipe.recipe.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import smile.iceBulterrecipe.recipe.entity.RecipeListCategory;
import smile.iceBulterrecipe.user.entity.User;
import smile.iceBulterrecipe.user.exception.UserNotFoundException;
import smile.iceBulterrecipe.user.repository.UserRepository;

@RequiredArgsConstructor
@Service
public class RecipeServiceImpl implements RecipeService{
    private final UserRepository userRepository;

    @Override
    public void getRecipeMainLists(Long userIdx, String recipeListCategory) {
        User user = this.userRepository.findByUserIdxAndIsEnable(userIdx, true).orElseThrow(UserNotFoundException::new);

        if(RecipeListCategory.getFoodCategoryByName(recipeListCategory).equals(RecipeListCategory.FRIDGEFOODRECIPE)){
            // 일단 ,, 인기부터 하고 고민할래요 .. 허헣.....
        }else if(RecipeListCategory.getFoodCategoryByName(recipeListCategory).equals(RecipeListCategory.POPULARRECIPE)){
            
        }

    }
}
