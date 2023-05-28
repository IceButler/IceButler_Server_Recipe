package smile.iceBulterrecipe.global.utils.redis;//package com.example.icebutler_server.global.util;
//
//
//
//import com.sun.istack.NotNull;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.HashMap;
//
//@RequiredArgsConstructor
//@Service
//public class RedisTemplateServiceMock implements RedisTemplateService {
//
//    private final HashMap<Long, String> redisTemplate;
//
//    public void deleteUserRefreshToken(@NotNull Long userIdx){
////    ValueOperations<Long, String> valueOperations = redisTemplate.opsForValue();
////    valueOperations.getAndDelete(userIdx);
//        redisTemplate.remove(userIdx);
//    }
//
//    //    @Nullable
//    @Transactional(readOnly = true)
//    public String getUserRefreshToken(@NotNull Long userIdx) {
////    ValueOperations<Long, String> valueOperations = redisTemplate.opsForValue();
////    return valueOperations.get(userIdx);
//        return redisTemplate.get(userIdx);
//    }
//
//    public void setUserRefreshToken(@NotNull Long userIdx, String refreshToken) {
//        redisTemplate.put(userIdx, refreshToken);
//    }
//}
