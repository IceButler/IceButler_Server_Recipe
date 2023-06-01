package smile.iceBulterrecipe.food.service;

import org.springframework.stereotype.Service;
import smile.iceBulterrecipe.food.dto.request.FoodReq;

@Service
public interface FoodService {
    void addFood(FoodReq foodReq);

  void deleteFood(FoodReq foodReq);
}
