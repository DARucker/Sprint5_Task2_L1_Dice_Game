package com.sprint5.task2.level1.dice.game.repository;

import com.sprint5.task2.level1.dice.game.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PlayerRepository extends JpaRepository<Player, Integer> {
    Optional<Player> findByName(String name);
}
