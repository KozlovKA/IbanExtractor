package by.kozlov.ibatest.ibanextractor.util;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class IbanExtractorUtil {

    private static final String IBAN_REGEX =
            "AL(?:0[2-9]|[1-8][0-9]|9[0-8])\\s?([0-9]{4}\\s?){2}([a-zA-Z0-9]{4}\\s?){4}\\s?|" +
                    "AD(?:0[2-9]|[1-8][0-9]|9[0-8])\\s?([0-9]{4}\\s?){2}([a-zA-Z0-9]{4}\\s?){3}\\s?|" +
                    "AT(?:0[2-9]|[1-8][0-9]|9[0-8])\\s?([0-9]{4}\\s?){4}\\s?|" +
                    "AZ(?:0[2-9]|[1-8][0-9]|9[0-8])\\s?([a-zA-Z0-9]{4}\\s?)([0-9]{4}\\s?){5}\\s?|" +
                    "BH(?:0[2-9]|[1-8][0-9]|9[0-8])\\s?([a-zA-Z]{4}\\s?)([a-zA-Z0-9]{4}\\s?){3}([a-zA-Z0-9]{2})\\s?|" +
                    "BY(?:0[2-9]|[1-8][0-9]|9[0-8])\\s?([a-zA-Z0-9]{4}\\s?)([0-9]{4}\\s?){5}\\s?|" +
                    "BE(?:0[2-9]|[1-8][0-9]|9[0-8])\\s?([0-9]{4}\\s?){3}\\s?|" +
                    "BA(?:0[2-9]|[1-8][0-9]|9[0-8])\\s?([0-9]{4}\\s?){4}\\s?|" +
                    "BR(?:0[2-9]|[1-8][0-9]|9[0-8])\\s?([0-9]{4}\\s?)([0-9]{4}\\s?)([0-9]{4}\\s?)([0-9]{4}\\s?)([0-9]{4}\\s?)([0-9]{3}\\s?[A-Z]\\s?)[A-Z0-9]\\s?|" +
                    "BG(?:0[2-9]|[1-8][0-9]|9[0-8])\\s?([a-zA-Z]{4}\\s?)([0-9]{4}\\s?)([0-9]{2})([a-zA-Z0-9]{2}\\s?)([a-zA-Z0-9]{4}\\s?)([a-zA-Z0-9]{2})\\s?|" +
                    "CR(?:0[2-9]|[1-8][0-9]|9[0-8])\\s?([0-9]{4}\\s?){4}([0-9]{2})\\s?|" +
                    "HR(?:0[2-9]|[1-8][0-9]|9[0-8])\\s?([0-9]{4}\\s?){4}([0-9])\\s?|" +
                    "CY(?:0[2-9]|[1-8][0-9]|9[0-8])\\s?([0-9]{4}\\s?){2}([a-zA-Z0-9]{4}\\s?){4}\\s?|" +
                    "CZ(?:0[2-9]|[1-8][0-9]|9[0-8])\\s?([0-9]{4}\\s?){5}\\s?|" +
                    "DK(?:0[2-9]|[1-8][0-9]|9[0-8])\\s?([0-9]{4}\\s?){3}([0-9]{2})\\s?|" +
                    "DO(?:0[2-9]|[1-8][0-9]|9[0-8])\\s?([a-zA-Z]{4}\\s?)([0-9]{4}\\s?){5}\\s?|" +
                    "TL(?:0[2-9]|[1-8][0-9]|9[0-8])\\s?([0-9]{4}\\s?){4}([0-9]{3})\\s?|" +
                    "EE(?:0[2-9]|[1-8][0-9]|9[0-8])\\s?([0-9]{4}\\s?){4}\\s?|" +
                    "FO(?:0[2-9]|[1-8][0-9]|9[0-8])\\s?([0-9]{4}\\s?){3}([0-9]{2})\\s?|" +
                    "FI(?:0[2-9]|[1-8][0-9]|9[0-8])\\s?([0-9]{4}\\s?){3}([0-9]{2})\\s?|" +
                    "FR(?:0[2-9]|[1-8][0-9]|9[0-8])\\s?([0-9]{4}\\s?){2}([0-9]{2})([a-zA-Z0-9]{2}\\s?)([a-zA-Z0-9]{4}\\s?){2}([a-zA-Z0-9])([0-9]{2})\\s?|" +
                    "GE(?:0[2-9]|[1-8][0-9]|9[0-8])\\s?([a-zA-Z0-9]{2})([0-9]{2}\\s?)([0-9]{4}\\s?){3}([0-9]{2})\\s?|" +
                    "DE(?:0[2-9]|[1-8][0-9]|9[0-8])\\s?([0-9]{4}\\s?){4}([0-9]{2})\\s?|" +
                    "GI(?:0[2-9]|[1-8][0-9]|9[0-8])\\s?([a-zA-Z]{4}\\s?)([a-zA-Z0-9]{4}\\s?){3}([a-zA-Z0-9]{3})\\s?|" +
                    "GR(?:0[2-9]|[1-8][0-9]|9[0-8])\\s?([0-9]{4}\\s?)([0-9]{3})([a-zA-Z0-9]\\s?)([a-zA-Z0-9]{4}\\s?){3}([a-zA-Z0-9]{3})\\s?|" +
                    "GL(?:0[2-9]|[1-8][0-9]|9[0-8])\\s?([0-9]{4}\\s?){3}([0-9]{2})\\s?|" +
                    "GT(?:0[2-9]|[1-8][0-9]|9[0-8])\\s?([a-zA-Z0-9]{4}\\s?)([a-zA-Z0-9]{4}\\s?){5}\\s?|" +
                    "HU(?:0[2-9]|[1-8][0-9]|9[0-8])\\s?([0-9]{4}\\s?){6}\\s?|" +
                    "IS(?:0[2-9]|[1-8][0-9]|9[0-8])\\s?([0-9]{4}\\s?){5}([0-9]{2})\\s?|" +
                    "IE(?:0[2-9]|[1-8][0-9]|9[0-8])\\s?([a-zA-Z0-9]{4}\\s?)([0-9]{4}\\s?){3}([0-9]{2})\\s?|" +
                    "IL(?:0[2-9]|[1-8][0-9]|9[0-8])\\s?([0-9]{4}\\s?){4}([0-9]{3})\\s?|" +
                    "IT(?:0[2-9]|[1-8][0-9]|9[0-8])\\s?([a-zA-Z])([0-9]{3}\\s?)([0-9]{4}\\s?)([0-9]{3})([a-zA-Z0-9]\\s?)([a-zA-Z0-9]{4}\\s?){2}([a-zA-Z0-9]{3})\\s?|" +
                    "JO(?:0[2-9]|[1-8][0-9]|9[0-8])\\s?([a-zA-Z]{4}\\s?)([0-9]{4}\\s?){5}([0-9]{2})\\s?|" +
                    "KZ(?:0[2-9]|[1-8][0-9]|9[0-8])\\s?([0-9]{4}\\s?){3}([0-9])([a-zA-Z0-9]{3}\\s?)([a-zA-Z0-9]{4}\\s?){2}([a-zA-Z0-9]{2})\\s?|" +
                    "XK(?:0[2-9]|[1-8][0-9]|9[0-8])\\s?([0-9]{4}\\s?)([0-9]{4}\\s?){2}([0-9]{2})([0-9]{2}\\s?)\\s?|" +
                    "KW(?:0[2-9]|[1-8][0-9]|9[0-8])\\s?([a-zA-Z]{4}\\s?)([a-zA-Z0-9]{4}\\s?){5}([a-zA-Z0-9]{2})\\s?|" +
                    "LV(?:0[2-9]|[1-8][0-9]|9[0-8])\\s?([a-zA-Z]{4}\\s?)([a-zA-Z0-9]{4}\\s?){3}([a-zA-Z0-9])\\s?|" +
                    "LB(?:0[2-9]|[1-8][0-9]|9[0-8])\\s?([0-9]{4}\\s?)([a-zA-Z0-9]{4}\\s?){5}\\s?|" +
                    "LC(?:0[2-9]|[1-8][0-9]|9[0-8])\\s?([a-zA-Z]{4}\\s?)([a-zA-Z0-9]{4}\\s?){6}\\s?|" +
                    "LI(?:0[2-9]|[1-8][0-9]|9[0-8])\\s?([0-9]{4}\\s?)([0-9])([a-zA-Z0-9]{3}\\s?)([a-zA-Z0-9]{4}\\s?){2}([a-zA-Z0-9])\\s?|" +
                    "LT(?:0[2-9]|[1-8][0-9]|9[0-8])\\s?([0-9]{4}\\s?){4}\\s?|" +
                    "LU(?:0[2-9]|[1-8][0-9]|9[0-8])\\s?([0-9]{3})([a-zA-Z0-9]\\s?)([a-zA-Z0-9]{4}\\s?){3}\\s?|" +
                    "MK(?:0[2-9]|[1-8][0-9]|9[0-8])\\s?([0-9]{3})([a-zA-Z0-9]\\s?)([a-zA-Z0-9]{4}\\s?){2}([a-zA-Z0-9])([0-9]{2})\\s?|" +
                    "MT(?:0[2-9]|[1-8][0-9]|9[0-8])\\s?([a-zA-Z]{4}\\s?)([0-9]{4}\\s?)([0-9])([a-zA-Z0-9]{3}\\s?)([a-zA-Z0-9]{4}\\s?){3}([a-zA-Z0-9]{3})\\s?|" +
                    "MR(?:0[2-9]|[1-8][0-9]|9[0-8])\\s?([0-9]{4}\\s?){5}([0-9]{3})\\s?|" +
                    "MU(?:0[2-9]|[1-8][0-9]|9[0-8])\\s?([a-zA-Z]{4}\\s?)([0-9]{4}\\s?){4}([0-9]{3})\\s?([a-zA-Z]\\s?)([a-zA-Z]{2})\\s?|" +
                    "MC(?:0[2-9]|[1-8][0-9]|9[0-8])\\s?([0-9]{4}\\s?){2}([0-9]{2})([a-zA-Z0-9]{2}\\s?)([a-zA-Z0-9]{4}\\s?){2}([a-zA-Z0-9])([0-9]{2})\\s?|" +
                    "MD(?:0[2-9]|[1-8][0-9]|9[0-8])\\s?([a-zA-Z0-9]{2})([a-zA-Z0-9]{2}\\s?)([a-zA-Z0-9]{4}\\s?){4}\\s?|" +
                    "ME(?:0[2-9]|[1-8][0-9]|9[0-8])\\s?([0-9]{4}\\s?){4}([0-9]{2})\\s?|" +
                    "NL(?:0[2-9]|[1-8][0-9]|9[0-8])\\s?([a-zA-Z]{4}\\s?)([0-9]{4}\\s?){2}([0-9]{2})\\s?|" +
                    "NO(?:0[2-9]|[1-8][0-9]|9[0-8])\\s?([0-9]{4}\\s?){2}([0-9]{3})\\s?|" +
                    "PK(?:0[2-9]|[1-8][0-9]|9[0-8])\\s?([a-zA-Z0-9]{4}\\s?)([0-9]{4}\\s?){4}\\s?|" +
                    "PS(?:0[2-9]|[1-8][0-9]|9[0-8])\\s?([a-zA-Z0-9]{4}\\s?)([0-9]{4}\\s?){5}([0-9])\\s?|" +
                    "PL(?:0[2-9]|[1-8][0-9]|9[0-8])\\s?([0-9]{4}\\s?){6}\\s?|" +
                    "PT(?:0[2-9]|[1-8][0-9]|9[0-8])\\s?([0-9]{4}\\s?){5}([0-9])\\s?|" +
                    "QA(?:0[2-9]|[1-8][0-9]|9[0-8])\\s?([a-zA-Z]{4}\\s?)([a-zA-Z0-9]{4}\\s?){5}([a-zA-Z0-9])\\s?|" +
                    "RO(?:0[2-9]|[1-8][0-9]|9[0-8])\\s?([a-zA-Z]{4}\\s?)([a-zA-Z0-9]{4}\\s?){4}\\s?|" +
                    "SM(?:0[2-9]|[1-8][0-9]|9[0-8])\\s?([a-zA-Z])([0-9]{3}\\s?)([0-9]{4}\\s?)([0-9]{3})([a-zA-Z0-9]\\s?)([a-zA-Z0-9]{4}\\s?){2}([a-zA-Z0-9]{3})\\s?|" +
                    "SA(?:0[2-9]|[1-8][0-9]|9[0-8])\\s?([0-9]{2})([a-zA-Z0-9]{2}\\s?)([a-zA-Z0-9]{4}\\s?){4}\\s?|" +
                    "RS(?:0[2-9]|[1-8][0-9]|9[0-8])\\s?([0-9]{4}\\s?){4}([0-9]{2})\\s?|" +
                    "SK(?:0[2-9]|[1-8][0-9]|9[0-8])\\s?([0-9]{4}\\s?){5}\\s?|" +
                    "SI(?:0[2-9]|[1-8][0-9]|9[0-8])\\s?([0-9]{4}\\s?){3}([0-9]{3})\\s?|" +
                    "ES(?:0[2-9]|[1-8][0-9]|9[0-8])\\s?([0-9]{4}\\s?){5}\\s?|" +
                    "SE(?:0[2-9]|[1-8][0-9]|9[0-8])\\s?([0-9]{4}\\s?){5}\\s?|" +
                    "CH(?:0[2-9]|[1-8][0-9]|9[0-8])\\s?([0-9]{4}\\s?)([0-9])([a-zA-Z0-9]{3}\\s?)([a-zA-Z0-9]{4}\\s?){2}([a-zA-Z0-9])\\s?|" +
                    "TN(?:0[2-9]|[1-8][0-9]|9[0-8])\\s?([0-9]{4}\\s?){5}\\s?|" +
                    "TR(?:0[2-9]|[1-8][0-9]|9[0-8])\\s?([0-9]{4}\\s?)([0-9])([a-zA-Z0-9]{3}\\s?)([a-zA-Z0-9]{4}\\s?){3}([a-zA-Z0-9]{2})\\s?|" +
                    "AE(?:0[2-9]|[1-8][0-9]|9[0-8])\\s?([0-9]{3})([0-9]\\s?)([0-9]{4}\\s?){3}([0-9]{3})\\s?|" +
                    "GB(?:0[2-9]|[1-8][0-9]|9[0-8])\\s?([a-zA-Z]{4}\\s?)([0-9]{4}\\s?){3}([0-9]{2})\\s?|" +
                    "VA(?:0[2-9]|[1-8][0-9]|9[0-8])\\s?([0-9]{3})([0-9]\\s?)([0-9]{4}\\s?){3}([0-9]{2})\\s?|" +
                    "VG(?:0[2-9]|[1-8][0-9]|9[0-8])\\s?([a-zA-Z0-9]{4}\\s?)([0-9]{4}\\s?){4}\\s?";

    public static List<String> extract(String text) {

        if (text == null) {
            return null;
        }
        text = text.replaceAll("\\p{Punct}|\\n?|\\t?", "").toUpperCase();

        List<String> ibanList = new ArrayList<>();
        Pattern ibanPattern = Pattern.compile(IBAN_REGEX, Pattern.MULTILINE);
        Matcher ibanMatcher = ibanPattern.matcher(text);
        while (ibanMatcher.find()) {
            ibanList.add(ibanMatcher.group());
        }
        return ibanList;
    }
}