package by.kozlov.ibatest.ibanextractor;

import com.fasterxml.jackson.databind.JsonSerializer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collection;
import java.util.Collections;

@SpringBootApplication
@EnableSwagger2
public class IbanExtractorApplication {

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(IbanExtractorApplication.class);
        application.setAdditionalProfiles("ssl");
        application.run(args);
    }

    @Bean
    public Docket swaggerConfiguration() {
        //Return a prepared Doket instance
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .paths(PathSelectors.ant("/*"))
                .apis(RequestHandlerSelectors.basePackage("by.kozlov"))
                .build()
                .apiInfo(apiDetails());
    }

    private ApiInfo apiDetails() {
        return new ApiInfo(
                "Address book API",
                "Sample API for IBAN extractor REST service",
                "1.0",
                "Free to use",
                new springfox.documentation.service.Contact("Kozlov Kirill", "https://www.linkedin.com/in/kozlovka/", "kirillkozlou@yandex.ru"),
                "API License",
                "",
                Collections.emptyList());

    }
}

