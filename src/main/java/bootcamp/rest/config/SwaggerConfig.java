package bootcamp.rest.config;

/*
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.service.Contact;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import java.util.Collection;

@Configuration
@EnableSwagger2
*/
public class SwaggerConfig {
    
    /* 
    @Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
        .select()
        .apis(RequestHandlerSelectors.basePackage(bootcamp.rest.controllers))
        .paths(PathSelectors.any())
        .build()
        .apiInfo(apiInfo);
    }

    private ApiInfo apiInfo(){
        
        APiInfo apiInfo = new ApiInfo(
            "Wclouds News API",
            "Restfull API for News Application",
            "v1.0",
            "Terms of Service",
            new Contact("Wclouds","https://www.github.com/wclouds19","wan.essentials@gmail.com"),
            "MIT License",
            "https://www.mit.edu/~amini/LICENSE.md",
            Collection.emptyList()
        );

        return apiInfo;
    }
    */
}
