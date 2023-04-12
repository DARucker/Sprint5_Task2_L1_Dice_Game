package com.sprint5.task2.level1.dice.game;

import com.sprint5.task2.level1.dice.game.entity.Game;
import com.sprint5.task2.level1.dice.game.entity.Player;
import com.sprint5.task2.level1.dice.game.repository.PlayerRepository;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
public class PlayerRepositoryMockTest {

    @Autowired
    private PlayerRepository playerRepository;

    @Test
    public void WhenSave_ThenReturnNotNull(){
        Player saved = playerRepository.save(new Player(1, "Dario"));
        assertThat(saved).isNotNull();
    }
    @Test
    public void WhenSave_ThenFindById() {
        Player saved = playerRepository.save(new Player(1, "Dario"));
        Optional<Player> found = playerRepository.findById(1);
        Player expected;
        if (found.isPresent()) {
            expected = found.get();
            assertThat(expected.getName()).isEqualTo(saved.getName());
        }
    }
    @Test
    public void WhenSaveTwoPlayers_ThenFindAllSizeIsTwo(){
        Player saved = playerRepository.save(new Player(1, "Dario"));
        Player saved1 = playerRepository.save(new Player(2, "Manu"));
        List<Player> expected = playerRepository.findAll();
        assertThat(expected.size()).isNotNull();
        assertThat(expected.size()).isEqualTo(2);
    }
    @Test
    public void WhenFindByName_ThenReturnPlayer(){
        Player saved = playerRepository.save(new Player(1, "Dario"));
        Player expected = playerRepository.findByName("Dario");
        assertThat(expected.getName()).isEqualTo("Dario");
    }

    @Test
    public void WhenDelete_ThenReturnNull(){

        Player saved = playerRepository.save(new Player(1, "Dario"));
        Player expected = playerRepository.findByName(saved.getName());
        assertThat(expected).isNotNull();
        playerRepository.delete(saved);
        expected = playerRepository.findByName(saved.getName());
        assertThat(expected).isNull();
    }
}
