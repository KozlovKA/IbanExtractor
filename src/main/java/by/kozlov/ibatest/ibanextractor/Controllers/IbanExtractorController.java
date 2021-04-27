package by.kozlov.ibatest.ibanextractor.Controllers;

import by.kozlov.ibatest.ibanextractor.Dto.IbanDTO;
import by.kozlov.ibatest.ibanextractor.Entity.Input;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import static by.kozlov.ibatest.ibanextractor.Util.IbanExtractorUtil.extract;

@RestController
@Slf4j
public class IbanExtractorController {

    @PostMapping(value = "/extract")
    ResponseEntity<List<IbanDTO>> listOfExtractedIbansAndShowHomeView(Input input) {

        log.info("Requested request to extract IBAN-s from Input model");

        List<String> extractedIbansList = extract(input.getValue());

        if (extractedIbansList == null){
            return null;
        }

        List<IbanDTO> ibanDTOList = new ArrayList<>();

        for (String iban : extractedIbansList) {
            ibanDTOList.add(new IbanDTO(iban));
        }

        log.info("Extracted IBAN-s: " + ibanDTOList);

        return ResponseEntity.ok(ibanDTOList);
    }
}