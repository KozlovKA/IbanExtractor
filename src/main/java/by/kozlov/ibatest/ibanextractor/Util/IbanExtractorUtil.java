package by.kozlov.ibatest.ibanextractor.Util;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class IbanExtractorUtil {

    private static final String IBAN_REGEX = "([A-Z]{2}[ \\-]?[0-9]{2})(?=(?:[ \\-]?[A-Z0-9]){9,30}$)((?:[ \\-]?[A-Z0-9]{3,5}){2,7})([ \\-]?[A-Z0-9]{1,3})?";

    public static List<String> extract(String text) {

        if (text == null) {
            return null;
        }

        List<String> ibanList = new ArrayList<>();
        Pattern ibanPattern = Pattern.compile(IBAN_REGEX);
        Matcher ibanMatcher = ibanPattern.matcher(text);
        while (ibanMatcher.find()) {
            ibanList.add(ibanMatcher.group());
        }
        return ibanList;
    }
}