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

import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor
public class AwsSqsListener {

	private final ObjectMapper objectMapper;
	private final FoodServiceImpl foodService;

	@Value("${aws.sqs.queue.url}")
	private String queueUrl;

	@SqsListener(value = "${aws.sqs.queue.name}", deletionPolicy = SqsMessageDeletionPolicy.NEVER)
	public void listen(@Payload String info, @Headers Map<String, String> headers, Acknowledgment ack) throws JsonProcessingException {
		log.info("-------------------------------------start SqsListener");
		log.info("-------------------------------------info {}", info);

		// TODO 메인 서버와 레시피 서버 중 한 곳에서만 listen 되는 문제
		FoodData foodData = objectMapper.readValue(info, FoodData.class);
		foodService.addFood(FoodReq.toEntity(foodData));

		ack.acknowledge();
	}
}