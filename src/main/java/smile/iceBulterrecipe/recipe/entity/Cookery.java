package smile.iceBulterrecipe.recipe.entity;

import javax.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import smile.iceBulterrecipe.global.BaseEntity;

@NoArgsConstructor(access= AccessLevel.PROTECTED)
@Getter
@Entity
public class Cookery extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Long cookeryIdx;

    private Long nextIdx;
    private String cookeryImg;
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="recipeIdx")
    private Recipe recipe;
}
