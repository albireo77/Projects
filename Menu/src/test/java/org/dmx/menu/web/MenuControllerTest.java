package org.dmx.menu.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.dmx.menu.error.ApplicationError;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = MenuController.class)
class MenuControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MenuService menuService;

    private ObjectMapper mapper = new ObjectMapper();

    @Test
    void testOkRequest() throws Exception {
        mockMvc.perform(post("/menu")
                .contentType("application/json")
                .content(fromFile("ok.json")))
                .andExpect(status().isCreated());
    }

    @Test
    void testBadRequestWith4Groups() throws Exception {

        String response = mockMvc.perform(post("/menu")
                .contentType("application/json")
                .content(fromFile("4groups.json")))
                .andExpect(status().isBadRequest())
                .andReturn()
                .getResponse()
                .getContentAsString();

        ApplicationError error = mapper.readValue(response, ApplicationError.class);
        assertEquals("Set must have 1-3 groups", error.getErrorMessage());
    }

    private byte[] fromFile(String path) throws IOException {
        return new ClassPathResource(path).getInputStream().readAllBytes();
    }
}