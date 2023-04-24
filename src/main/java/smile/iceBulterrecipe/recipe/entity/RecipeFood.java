package smile.iceBulterrecipe.recipe.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import smile.iceBulterrecipe.food.entity.Food;
import smile.iceBulterrecipe.global.BaseEntity;

import javax.persistence.*;

@NoArgsConstructor(access= AccessLevel.PROTECTED)
@Getter
@Entity
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
