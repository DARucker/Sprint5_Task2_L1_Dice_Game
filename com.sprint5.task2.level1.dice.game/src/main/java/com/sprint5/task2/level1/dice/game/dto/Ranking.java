package com.sprint5.task2.level1.dice.game.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ranking {
    private int id;
    private int playerId;
    private Long win;
    private Long played;
    private int ratio;


}
