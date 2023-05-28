package smile.iceBulterrecipe.global.sqs;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.awspring.cloud.messaging.listener.Acknowledgment;
import io.awspring.cloud.messaging.listener.SqsMessageDeletionPolicy;
import io.awspring.cloud.messaging.listener.annotation.SqsListener;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import smile.iceBulterrecipe.food.dto.request.FoodReq;
import smile.iceBulterrecipe.food.service.FoodServiceImpl;
import smile.iceBulterrecipe.global.utils.redis.RedisUtils;
import smile.iceBulterrecipe.global.utils.redis.SyncData;

import java.util.Map;
import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class AwsSqsListener {

	private final ObjectMapper objectMapper;
	private final FoodServiceImpl foodService;
	private final RedisUtils redisUtils;

	@Value("${aws.sqs.queue.url}")
	private String queueUrl;

	@SqsListener(value = "${aws.sqs.queue.name}", deletionPolicy = SqsMessageDeletionPolicy.NEVER)
	public void listen(@Payload String info, @Headers Map<String, String> headers, Acknowledgment ack) throws JsonProcessingException {
		log.info("-------------------------------------start SqsListener");
		log.info("-------------------------------------info {}", info);
		FoodData foodData = null;
		try{
			// 음식 데이터 json으로 변환
			foodData = objectMapper.readValue(info, FoodData.class);
		}catch (JsonProcessingException e){
			throw new RuntimeException(e);
		}
		Optional<SyncData> optionalData = redisUtils.get(foodData.getUuid(), SyncData.class);
		if(optionalData.isPresent()){
			SyncData syncData = optionalData.get();
			if(!syncData.isRecipeService()){
				//1. 음식 데이터 저장
//				foodService.addFood(foodData.toFoodReq());
				System.out.println(" 음식 데이터 저장! ");
				//2. 레디스의 동기화용 데이터 삭제
				redisUtils.delete(foodData.getUuid());
				//3. 큐에 있는 음식 데이터 삭제
				ack.acknowledge();
			}
		}
		log.info("음식 데이터 receive : {}", foodData);
	}
}