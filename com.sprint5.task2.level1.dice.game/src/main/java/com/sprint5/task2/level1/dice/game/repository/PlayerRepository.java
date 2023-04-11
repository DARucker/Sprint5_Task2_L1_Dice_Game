package com.sprint5.task2.level1.dice.game.repository;

import com.sprint5.task2.level1.dice.game.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<Player, Integer> {
}
