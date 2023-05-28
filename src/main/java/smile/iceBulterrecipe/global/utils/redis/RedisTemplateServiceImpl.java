package smile.iceBulterrecipe.global.utils.redis;

import com.sun.istack.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class RedisTemplateServiceImpl implements RedisTemplateService {

    private final RedisTemplate<String, String> redisTemplate;

    public void deleteUserRefreshToken(String userIdx){
        if(redisTemplate.opsForValue().get(userIdx)!=null) redisTemplate.delete(userIdx);
    }

    //    @Nullable
    @Transactional(readOnly = true)
    public String getUserRefreshToken(@NotNull String userIdx) {
        return redisTemplate.opsForValue().get(userIdx);
    }

    public void setUserRefreshToken(@NotNull String userIdx, String refreshToken) {
        redisTemplate.opsForValue().set(userIdx, refreshToken);
    }
}
