package smile.iceBulterrecipe.admin.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import smile.iceBulterrecipe.global.feign.dto.request.AdminReq;

@Transactional(readOnly = true)
@Service
public class AdminServiceImpl implements AdminService{
    @Override
    public void addAdmin(AdminReq request) {
        // admin 추가
    }
}
