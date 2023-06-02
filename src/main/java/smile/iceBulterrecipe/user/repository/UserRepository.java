package smile.iceBulterrecipe.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import smile.iceBulterrecipe.user.entity.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUserIdxAndIsEnable(Long userIdx, Boolean isEnable);

    List<User> findByNicknameContains(String nickname);


    List<User> findAllByNicknameContains(String nickname);
}
