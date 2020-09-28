package org.dmx.pn.web;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

class EvaluationControllerTest {

    private MockMvc mockMvc;


    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(new EvaluationController("en-US", "%.2f")).build();
    }

    @Test
    public void testRequest() throws Exception {

        mockMvc.perform(post("/evaluate").contentType("text/plain").content("- -1.5 * 3.1415 / -7 -2"))
                .andExpect(status().isOk())
                .andExpect(content().string("-12.50"));
    }

    @Test
    public void testBadRequest() throws Exception {

        mockMvc.perform(post("/evaluate").contentType("text/plain").content("5 7"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("error"));
    }
}