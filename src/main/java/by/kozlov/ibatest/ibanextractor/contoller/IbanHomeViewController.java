package by.kozlov.ibatest.ibanextractor.contoller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class IbanHomeViewController {

    @GetMapping(value = "/")
    public String showInputForm() {
        log.info("Get iban home view requested");
        return "ibanHome";
    }
}