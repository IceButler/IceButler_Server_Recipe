package smile.iceBulterrecipe.global.sqs;


import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import com.amazonaws.services.sqs.model.SendMessageResult;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import smile.iceBulterrecipe.global.utils.redis.RedisTemplateService;
import smile.iceBulterrecipe.global.utils.redis.RedisUtils;
import smile.iceBulterrecipe.global.utils.redis.SyncData;

import java.util.UUID;

@RequiredArgsConstructor
@Component
public class AmazonSQSSenderImpl implements AmazonSQSSender{
    @Value("${aws.sqs.queue.url}")
    private String url;
    private final ObjectMapper objectMapper;
    private final AmazonSQS amazonSQS;
    private final RedisUtils redisUtils;


    @Override
    public SendMessageResult sendMessage(FoodData msg){
        SendMessageRequest sendMessageRequest = null;
        redisUtils.set(msg.getUuid(), new SyncData(), 10);
        try {
            sendMessageRequest = new SendMessageRequest(url, objectMapper.writeValueAsString(msg))
                    .withMessageGroupId("food-data")
                    .withMessageDeduplicationId(UUID.randomUUID().toString());
        }catch (JsonProcessingException e){
            throw new RuntimeException(e);
        }
        return amazonSQS.sendMessage(sendMessageRequest);
    }
}