package com.sprint5.task2.level1.dice.game;

import com.sprint5.task2.level1.dice.game.dto.Playerdto;
import com.sprint5.task2.level1.dice.game.entity.Player;
import com.sprint5.task2.level1.dice.game.repository.PlayerRepository;
import com.sprint5.task2.level1.dice.game.service.IPlayerSevice;
import com.sprint5.task2.level1.dice.game.service.PlayerServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@SpringBootTest
public class PlayerServiceMockTest {

    @Mock
    private PlayerRepository playerRepository;
    private IPlayerSevice playerService;
    private Player player1;
    private Playerdto playerdto1;
    //@Autowired
    //private PlayerServiceImpl playerService;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.openMocks(this);
        playerService = new PlayerServiceImpl(playerRepository);
        player1 = Player.builder()
                .id(1)
                .name("Player1")
                .registDate(null)
                .games(null).build();

        playerdto1 = Playerdto.builder()
                .id(1)
                .name("Playerdto1")
                .registDate(null)
                .gamesdto(null).build();
    }

    @DisplayName("Testing method FindByName")
    @Test
    public void WhenFindByName_ThenTestReturnEntity(){
        Mockito.when(playerRepository.findByName("Player1")).thenReturn(Optional.of(player1));
        Playerdto found = playerService.findByName("Player1");
        assertThat(found).isNotNull();
        assertThat(found.getName()).isEqualTo("Player1");
    }
    @DisplayName("Testing method FindByName Throw ResponseStatusException")
    @Test
    public void WhenFindByName_ThenThrowResponseStatusException(){

        //  TODO: testear la excepcion
    }


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
