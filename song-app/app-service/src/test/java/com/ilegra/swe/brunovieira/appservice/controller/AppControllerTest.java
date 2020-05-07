package com.ilegra.swe.brunovieira.appservice.controller;

import com.ilegra.swe.brunovieira.appservice.domain.Song;
import com.ilegra.swe.brunovieira.appservice.service.AppService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(AppController.class)
public class AppControllerTest {

    @MockBean
    private AppService appService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldReturnPlaylist() throws Exception {
        when(appService.listSongs(eq("id")))
                .thenReturn(Collections.singletonList(new Song("id", "Hallowed Be Thy Name")));

        mockMvc.perform(get("/app/v1/id"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].name", is("Hallowed Be Thy Name")));
    }
}
