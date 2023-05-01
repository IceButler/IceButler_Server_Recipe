package smile.iceBulterrecipe.food.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import smile.iceBulterrecipe.food.entity.Food;

import java.util.Optional;

public interface FoodRepository extends JpaRepository<Food, Long> {
    Optional<Food> findByFoodName(String foodName);
}
