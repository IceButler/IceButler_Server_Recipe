package smile.iceBulterrecipe.admin.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import smile.iceBulterrecipe.global.BaseEntity;
import smile.iceBulterrecipe.global.entityListener.UserEntityListener;

import javax.persistence.*;

@NoArgsConstructor(access= AccessLevel.PROTECTED)
@Getter
@Entity
@EntityListeners(UserEntityListener.class)
public class Admin extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Long adminIdx;
    @Column(nullable = false)
    private String email;
    private String password;
    private Boolean loginStatus;

    public void login() {
        this.loginStatus = true;
    }

    public Admin(String email, String password) {
        this.email = email;
        this.password = password;
    }
}