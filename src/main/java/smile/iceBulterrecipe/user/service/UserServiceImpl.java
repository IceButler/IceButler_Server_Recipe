package smile.iceBulterrecipe.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import smile.iceBulterrecipe.user.dto.assembler.UserAssembler;
import smile.iceBulterrecipe.user.dto.request.AddUserReq;
import smile.iceBulterrecipe.user.repository.UserRepository;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    private final UserAssembler userAssembler;

    @Transactional
    @Override
    public void addUser(AddUserReq userInfo) {
        this.userRepository.save(this.userAssembler.toEntity(userInfo));
    }
}