package smile.iceBulterrecipe.food.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import smile.iceBulterrecipe.food.entity.Food;

public interface FoodRepository extends JpaRepository<Food, Long> {
}
