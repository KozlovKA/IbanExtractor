package by.kozlov.ibatest.ibanextractor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class IbanExtractorApplication {

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(IbanExtractorApplication.class);
        application.setAdditionalProfiles("ssl");
        application.run(args);
    }
}

