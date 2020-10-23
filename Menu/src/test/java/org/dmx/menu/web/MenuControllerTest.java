package org.dmx.menu.web;

import org.dmx.menu.service.MenuService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.io.IOException;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = MenuController.class)
class MenuControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MenuService menuService;

    @Test
    void testOkRequest() throws Exception {
        mockMvc.perform(post("/menu")
                .contentType("application/json")
                .content(fromFile("ok.json")))
                .andExpect(status().isCreated());
    }

    @Test
    void testBadRequestWith4Groups() throws Exception {
        mockMvc.perform(post("/menu")
                .contentType("application/json")
                .content(fromFile("4groups.json")))
                .andExpect(status().isBadRequest())
                .andExpect(content().json("{\"errorMessage\":\"Set must have 1-3 groups\"}"));
    }

    private byte[] fromFile(String path) throws IOException {
        return new ClassPathResource(path).getInputStream().readAllBytes();
    }
}