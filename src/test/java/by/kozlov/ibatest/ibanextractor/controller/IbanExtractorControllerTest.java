package by.kozlov.ibatest.ibanextractor.controller;

import by.kozlov.ibatest.ibanextractor.entity.Input;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class IbanExtractorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;


    @Test
    void sendValidExtractRequest() throws Exception {
        Input input = new Input("BR15 0000 0000 0000 1093 2840 814 P2");

        this.mockMvc.perform(post("/extract")
                .secure(true)
                .content(objectMapper.writeValueAsString(input)))
                .andExpect(status().isOk());
    }
}