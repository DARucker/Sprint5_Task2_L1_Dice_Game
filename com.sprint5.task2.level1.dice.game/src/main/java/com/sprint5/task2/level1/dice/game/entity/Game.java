package com.sprint5.task2.level1.dice.game.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
@Entity
@Table(name = "games")
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private int points;
    private enum resultGame {Win,Loose};

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="playerId")
    private Player player;
}
