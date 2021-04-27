package by.kozlov.ibatest.ibanextractor.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

import static by.kozlov.ibatest.ibanextractor.util.IbanExtractorUtil.extract;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;


@SpringBootTest
class IbanExtractorUtilTest {

    @Test
    @DisplayName("Extract IBAN-s form empty input")
    void extractFromEmptyString() {
        assertNull(extract(null));
    }

    @Test
    @DisplayName("Extract IBAN-s from string that not contain any IBAN")
    void extractFromStringWithNoIbanOnIt() {
        String input = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.";
        assertEquals(new ArrayList<>(), extract(input));
    }

    @Test
    @DisplayName("Extract IBAN-s form string with some IBAN-s on it")
    void extractFromStringWithSomeIbanOnIt() {
        String input = "Country\tIBAN formatting example\n" +
                "Belgium\tBE71 0961 2345 6769\n" +
                "Brazil\tBR15 0000 0000 0000 1093 2840 814 P2\n" +
                "France\tFR76 3000 6000 0112 3456 7890 189\n" +
                "Germany\tDE91 1000 0000 0123 4567 89\n" +
                "Greece\tGR96 0810 0010 0000 0123 4567 890\n" +
                "Mauritius\tMU43 BOMM 0101 1234 5678 9101 000 MUR\n" +
                "Pakistan\tPK70 BANK 0000 1234 5678 9000\n" +
                "Poland\tPL10 1050 0099 7603 1234 5678 9123\n" +
                "Romania\tRO09 BCYP 0000 0012 3456 7890\n" +
                "Saint Lucia\tLC14 BOSL 1234 5678 9012 3456 7890 1234\n" +
                "Saudi Arabia\tSA4420000001234567891234\n" +
                "Spain\tES79 2100 0813 6101 2345 6789\n" +
                "Switzerland\tCH56 0483 5012 3456 7800 9\n" +
                "United Kingdom\tGB98 MIDL 0700 9312 3456 78";
        assertEquals(14, extract(input).size());
    }
}