package smile.iceBulterrecipe.user.service;

import smile.iceBulterrecipe.user.dto.request.UserReq;

public interface UserService {
    void addUser(UserReq userReq);

    void changeUserProfile(UserReq userReq);

    void deleteUser(UserReq userReq);
}
