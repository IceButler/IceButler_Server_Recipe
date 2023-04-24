package smile.iceBulterrecipe.food.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import smile.iceBulterrecipe.food.dto.assembler.FoodAssembler;
import smile.iceBulterrecipe.food.dto.request.FoodReq;
import smile.iceBulterrecipe.food.repository.FoodRepository;

@Service
@RequiredArgsConstructor
public class FoodServiceImpl implements FoodService{
    private final FoodAssembler foodAssembler;
    private final FoodRepository foodRepository;

    @Transactional
    @Override
    public void addFood(FoodReq foodReq) {
        this.foodRepository.save(this.foodAssembler.toEntity(foodReq));
    }
}
