package smile.iceBulterrecipe;

import io.awspring.cloud.autoconfigure.context.ContextRegionProviderAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableAsync;
import smile.iceBulterrecipe.global.feign.feignClient.MainServerClient;

@EnableAsync
@EnableFeignClients(clients = {MainServerClient.class})
@SpringBootApplication(exclude = ContextRegionProviderAutoConfiguration.class)
public class IceBulterRecipeApplication {

	public static void main(String[] args) {
		SpringApplication.run(IceBulterRecipeApplication.class, args);
	}

}