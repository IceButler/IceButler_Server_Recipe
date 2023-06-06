package smile.iceBulterrecipe.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import smile.iceBulterrecipe.admin.entity.Admin;

import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin, Long>, AdminRepositoryQuerydsl {
    Optional<Admin> findByAdminIdxAndIsEnable(Long adminIdx, Boolean isEnable);
}
