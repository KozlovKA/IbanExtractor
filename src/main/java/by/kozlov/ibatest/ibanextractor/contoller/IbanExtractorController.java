package by.kozlov.ibatest.ibanextractor.contoller;

import by.kozlov.ibatest.ibanextractor.dto.IbanDTO;
import by.kozlov.ibatest.ibanextractor.entity.Input;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import static by.kozlov.ibatest.ibanextractor.util.IbanExtractorUtil.extract;

@RestController
@Slf4j
public class IbanExtractorController {

    @PostMapping(value = "/extract")
    ResponseEntity<List<IbanDTO>> extractedIbansList(Input input) {

        log.info("Requested request to extract IBAN-s from Input request");

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