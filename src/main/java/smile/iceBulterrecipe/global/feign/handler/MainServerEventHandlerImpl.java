package smile.iceBulterrecipe.global.feign.handler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import smile.iceBulterrecipe.global.feign.feignClient.MainServerClient;

@Slf4j
@RequiredArgsConstructor
@Component
public class MainServerEventHandlerImpl implements MainServerEventHandler {
    private final MainServerClient mainServerClient;
}
