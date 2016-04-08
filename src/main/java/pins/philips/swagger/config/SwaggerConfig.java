package pins.philips.swagger.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


/**
 * Created by 310231492 on 2016/4/5.
 */
@Configuration
@EnableWebMvc
@EnableSwagger2
@ComponentScan(basePackages = {"pins.philips.swagger"})
public class SwaggerConfig {
    @Bean
    public Docket customDocket() {
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo());
    }

    private ApiInfo apiInfo()
    {
        ApiInfo apiInfo = new ApiInfo(
                "Swagger Demo",
                "SwaggerDemo controller description",
                "1.0",
                "xinxuan.dai@philips.com",
                "My Apps API Licence Type",
                "My Apps API License URL","");
        return apiInfo;
    }
}

