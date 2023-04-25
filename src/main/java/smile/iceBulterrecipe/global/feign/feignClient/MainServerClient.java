package smile.iceBulterrecipe.global.feign.feignClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;

@Component
@FeignClient(name="main-server", url = "${server.main.url}")
public interface MainServerClient {
}
