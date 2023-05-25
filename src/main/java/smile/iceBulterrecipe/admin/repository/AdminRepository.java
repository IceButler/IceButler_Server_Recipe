package smile.iceBulterrecipe.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import smile.iceBulterrecipe.admin.entity.Admin;

public interface AdminRepository extends JpaRepository<Admin, Long>, AdminRepositoryQuerydsl {
}
