package smile.iceBulterrecipe.global.sqs;

import com.amazonaws.services.sqs.model.SendMessageResult;

public interface AmazonSQSSender {
    SendMessageResult sendMessage(FoodData message);
}
