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

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;


@SpringBootTest
public class PlayerServiceMockTest {

    @Mock
    private PlayerRepository playerRepository;
    private IPlayerSevice playerService;
    private Player player1;
    private Playerdto playerdto1;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.openMocks(this);
        playerService = new PlayerServiceImpl(playerRepository);
        player1 = Player.builder()
                .id(0)
                .name("Player1")
                .registDate(Calendar.getInstance())
                .build();

        playerdto1 = Playerdto.builder()
                .id(0)
                .name("Playerdto1")
                .registDate(Calendar.getInstance())
                .build();
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
    public void whenCreate_testNameOfPlayer (){

        Mockito.when(playerRepository.findByName("Player1")).thenReturn(Optional.empty());

        Mockito.when(playerRepository.save(any())).thenReturn(player1);

        Player expected = playerService.create(playerdto1);//(playerService.entityToDto(player1));

        //assertThat(expected).isNull();
        assertThat(expected.getName()).isEqualTo("Player2");
    }


    @DisplayName("Testing method dto To Entity")
    @Test
    public void whenReciveDto_ThenReturnEntity(){

        Playerdto playerdto = new Playerdto(1, "Dario", Calendar.getInstance());
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
