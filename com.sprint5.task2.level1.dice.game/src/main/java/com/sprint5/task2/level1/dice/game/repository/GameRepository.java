package com.sprint5.task2.level1.dice.game.repository;

import com.sprint5.task2.level1.dice.game.entity.Game;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<Game, Integer> {
}
