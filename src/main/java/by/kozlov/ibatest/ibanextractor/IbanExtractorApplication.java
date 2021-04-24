package by.kozlov.ibatest.ibanextractor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class IbanExtractorApplication {

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(IbanExtractorApplication.class);
        application.setAdditionalProfiles("ssl");
        application.run(args);
    }
}

