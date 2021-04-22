package by.kozlov.ibatest.ibanextractor.Controllers;

import by.kozlov.ibatest.ibanextractor.Entity.Input;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static by.kozlov.ibatest.ibanextractor.Util.IbanExtractorUtil.extract;

@Controller
@Slf4j
public class IbanController {


    @GetMapping(value = "/")
    public String showInputForm(Model model) {
        model.addAttribute("input", new Input());
        return "ibanHome";
    }

    @PostMapping(value = "/extract")
    public String submit(@ModelAttribute("iban") Input input,
                         BindingResult result, ModelMap model) {
        if (result.hasErrors()) {
            return "error";
        }

        List<String> ibanList = extract(input.getValue());

        model.addAttribute("result", ibanList);
        return "result";
    }
}

