package smile.iceBulterrecipe.global.feign.publisher;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MainServerEventPublisherImpl implements MainServerEventPublisher {

    private final ApplicationEventPublisher publisher;

}
