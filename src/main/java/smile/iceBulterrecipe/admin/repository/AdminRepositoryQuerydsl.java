package smile.iceBulterrecipe.admin.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import smile.iceBulterrecipe.admin.dto.response.UserResponse;

public interface AdminRepositoryQuerydsl {
    Page<UserResponse> findAllByNicknameAndActive(Pageable pageable, String nickname, boolean active, boolean order);
}
