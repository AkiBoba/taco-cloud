package com.example.tacocloud;

import com.example.tacocloud.web.HomeController;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static
        org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static
        org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static
        org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static
        org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@Slf4j
@WebMvcTest(HomeController.class)
public class HomeControllerTest {

    private MockMvc mockMvc;

    @Test
    public void testHomePage() {
        try {
            mockMvc.perform(get("/")).andExpect(status().isOk()).andExpect(view().name("home")).andExpect(content().string(containsString("Welcome to...")));

        } catch (Exception e) {
            log.error("ошибка тестового соединения с домашней страницей {}", e.getMessage());
        }
    }
}
