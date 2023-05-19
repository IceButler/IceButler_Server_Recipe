package smile.iceBulterrecipe.admin.service;

import smile.iceBulterrecipe.global.feign.dto.request.AdminReq;

public interface AdminService {
    void addAdmin(AdminReq request);
}
