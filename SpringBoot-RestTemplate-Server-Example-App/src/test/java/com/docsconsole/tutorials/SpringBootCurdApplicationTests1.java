/*
package com.docsconsole.tutorials;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest
public class SpringBootCurdApplicationTests1 {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testWeb_WhenSuccessIsResult() throws Exception {
        this.mockMvc.perform(get("/curd-app/api/testWeb")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Result: Success")));
    }

}


*/
