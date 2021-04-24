package by.kozlov.ibatest.ibanextractor.Controllers;

import by.kozlov.ibatest.ibanextractor.Dto.IbanDTO;

import java.util.ArrayList;

import by.kozlov.ibatest.ibanextractor.Entity.Input;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.google.gson.Gson;

import java.util.List;

import static by.kozlov.ibatest.ibanextractor.Util.IbanExtractorUtil.extract;

@Controller
@Slf4j
public class IbanController {


    @GetMapping(value = "/")
    public String showInputForm(Model model) {
        log.info("Get iban home view requested");
        model.addAttribute("input", new Input());
        return "ibanHome";
    }

    @PostMapping(value = "/extract")
    String listOfExtractedIbansAndShowHomeView(@ModelAttribute("iban") Input input, BindingResult result, ModelMap model) {

        List<String> extractedIbansList = extract(input.getValue());
        List<IbanDTO> ibanDTOList = new ArrayList<>();

        for (String iban : extractedIbansList) {
            ibanDTOList.add(new IbanDTO(iban));
        }

        log.info("Extracted IBAN-s: " + ibanDTOList);

        model.addAttribute(ibanDTOList);
        model.addAttribute("input", input);
        return "ibanHome";
    }
}


