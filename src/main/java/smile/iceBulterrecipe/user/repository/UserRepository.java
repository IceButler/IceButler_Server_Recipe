package smile.iceBulterrecipe.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import smile.iceBulterrecipe.user.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}