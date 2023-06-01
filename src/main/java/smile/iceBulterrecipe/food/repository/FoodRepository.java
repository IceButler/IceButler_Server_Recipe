package smile.iceBulterrecipe.food.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import smile.iceBulterrecipe.food.entity.Food;

import java.util.Optional;
import java.util.UUID;

public interface FoodRepository extends JpaRepository<Food, Long> {
  Optional<Food> findByFoodName(String foodName);

  Optional<Food> findByUuidAndIsEnable(UUID uuid, boolean status);
}
