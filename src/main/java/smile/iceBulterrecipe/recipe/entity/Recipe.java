package smile.iceBulterrecipe.recipe.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import smile.iceBulterrecipe.global.BaseEntity;
import smile.iceBulterrecipe.global.entityListener.RecipeEntityListener;
import smile.iceBulterrecipe.user.entity.User;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access= AccessLevel.PROTECTED)
@Getter
@Entity
@SQLDelete(sql = "UPDATE recipe SET is_enable = false, update_at = current_timestamp WHERE recipe_idx = ?")
@EntityListeners(RecipeEntityListener.class)
public class Recipe extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Long recipeIdx;
    private String recipeName;
    private String recipeImgKey;
    private Integer quantity;
    @Enumerated(EnumType.STRING)
    private RecipeCategory recipeCategory;
    private Long leadTime;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="userIdx")
    private User user;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "recipe")
    @Where(clause = "recipelike0_.is_enable = 1")
    private List<RecipeLike> recipeLikes = new ArrayList<>();

    @Builder
    public Recipe(String recipeName, String recipeImgKey, Integer quantity, RecipeCategory recipeCategory, Long leadTime, User user) {
        this.recipeName = recipeName;
        this.recipeImgKey = recipeImgKey;
        this.quantity = quantity;
        this.recipeCategory = recipeCategory;
        this.leadTime = leadTime;
        this.user = user;
    }

    public void removeRecipe() {
        this.setIsEnable(false);
    }


    public void updateRecipe(String recipeName, String recipeImgKey, Integer quantity, Long leadTime,RecipeCategory recipeCategory) {
        this.recipeName = recipeName;
        this.recipeImgKey = recipeImgKey;
        this.quantity = quantity;
        this.leadTime = leadTime;
        this.recipeCategory=recipeCategory;
    }
}
