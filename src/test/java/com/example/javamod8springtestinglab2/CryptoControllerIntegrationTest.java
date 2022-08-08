package com.example.javamod8springtestinglab2;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.containsString;

@WebMvcTest(CryptoController.class)
public class CryptoControllerIntegrationTest {
    @Autowired
    private MockMvc mockMvc;
    // adding a JokeService here
    @MockBean
    private CryptoService cryptoService;

    @Test
    void shouldReturnSingleBitCoinPrice() throws Exception {
        mockMvc.perform(get("/crypto"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("The price of one Bitcoin is")));
    }
}
