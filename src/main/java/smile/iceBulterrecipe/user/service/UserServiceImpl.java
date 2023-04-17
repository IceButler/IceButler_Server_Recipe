package smile.iceBulterrecipe.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import smile.iceBulterrecipe.user.repository.UserRepository;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
}