package com.sprint5.task2.level1.dice.game;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sprint5.task2.level1.dice.game.controller.PlayerController;
import com.sprint5.task2.level1.dice.game.dto.Playerdto;
import com.sprint5.task2.level1.dice.game.service.IGameService;
import com.sprint5.task2.level1.dice.game.service.IPlayerSevice;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Calendar;

import static org.mockito.BDDMockito.given;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.CoreMatchers.is;


@WebMvcTest(PlayerController.class)
public class PlayerControllerMockTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private IPlayerSevice playerSevice;
    @MockBean
    private IGameService gameService;

    @Test
    public void testSavePlayer() throws Exception {
        Playerdto playerdto = Playerdto.builder()
                .id(1)
                .name("dario")
                .registDate (Calendar.getInstance())
                .build();

        given(playerSevice.create(any(Playerdto.class)))
                .willAnswer((invocation -> invocation.getArgument(0)));
        ResultActions response = mockMvc.perform(post("/player/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(playerdto)));
        response.andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", is("dario")))
                .andExpect(jsonPath("$.registDate", is(Calendar.getInstance())));
    }

}

