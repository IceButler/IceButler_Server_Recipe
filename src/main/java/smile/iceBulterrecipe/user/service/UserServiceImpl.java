package smile.iceBulterrecipe.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import smile.iceBulterrecipe.user.dto.assembler.UserAssembler;
import smile.iceBulterrecipe.user.dto.request.UserReq;
import smile.iceBulterrecipe.user.entity.User;
import smile.iceBulterrecipe.user.exception.UserNotFoundException;
import smile.iceBulterrecipe.user.repository.UserRepository;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    private final UserAssembler userAssembler;

    @Transactional
    @Override
    public void addUser(UserReq userReq) {
        this.userRepository.save(this.userAssembler.toEntity(userReq));
    }

    @Transactional
    @Override
    public void changeUserProfile(UserReq userReq) {
        User user = this.userRepository.findByUserIdxAndIsEnable(userReq.getUserIdx(), true).orElseThrow(UserNotFoundException::new);
        user.modifyProfile(userReq.getNickname(), userReq.getProfileImgKey());
    }
}