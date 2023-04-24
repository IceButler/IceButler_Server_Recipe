package smile.iceBulterrecipe.recipe.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import smile.iceBulterrecipe.food.entity.Food;
import smile.iceBulterrecipe.global.BaseEntity;

import javax.persistence.*;

@NoArgsConstructor(access= AccessLevel.PROTECTED)
@Getter
@Entity
@SQLDelete(sql = "UPDATE recipe_food SET is_enable = false, update_at = current_timestamp WHERE recipe_food_idx = ?")
public class RecipeFood extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Long recipeFoodIdx;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="recipeIdx")
    private Recipe recipe;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="foodIdx")
    private Food food;

    private String ingredientDetail;
}
