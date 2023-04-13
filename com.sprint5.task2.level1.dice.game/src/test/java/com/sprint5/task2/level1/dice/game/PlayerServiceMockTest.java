package com.sprint5.task2.level1.dice.game;

import com.sprint5.task2.level1.dice.game.dto.Playerdto;
import com.sprint5.task2.level1.dice.game.entity.Player;
import com.sprint5.task2.level1.dice.game.service.PlayerServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@SpringBootTest
public class PlayerServiceMockTest {

    @Autowired
    private PlayerServiceImpl playerService;


    @Test
    public void whenReciveDto_ThenReturnEntity(){

        Playerdto playerdto = new Playerdto(1, "Dario", null, null);
        Player expected = playerService.dtoToEntity(playerdto);
        assertThat(expected).isNotNull();
        assertThat(expected.getName()).isEqualTo("Dario");

    }
    @Test
    public void whenReciveEntity_ThenReturnDto(){

        Player player = new Player(1, "Dario");
        Playerdto expected = playerService.entityToDto(player);
        assertThat(expected).isNotNull();
        assertThat(expected.getName()).isEqualTo("Dario");

    }

}
