package smile.iceBulterrecipe.global.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import smile.iceBulterrecipe.global.resolver.AdminResolver;
import smile.iceBulterrecipe.global.resolver.LoginResolver;

import java.util.List;

@RequiredArgsConstructor
@Configuration
public class WebConfig implements WebMvcConfigurer {
    private final LoginResolver isLoginResolver;
    private final AdminResolver adminResolver;


    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(isLoginResolver);
        resolvers.add(adminResolver);

    }
}
