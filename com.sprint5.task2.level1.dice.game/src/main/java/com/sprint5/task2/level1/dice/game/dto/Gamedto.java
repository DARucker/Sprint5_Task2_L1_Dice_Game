package com.sprint5.task2.level1.dice.game.dto;

import com.sprint5.task2.level1.dice.game.entity.Player;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Gamedto {

    @Schema(description = "This is the id of the GAME. The id is autogenerated by the database")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Schema(description = "This are the points obtained. The calculation is done by the program")
    private int points;
    @Schema(description = "If you get 7 points from the dices this will be Win, otherwise will be Loose. The calculation is done by the program")
    private String resultGame;

    @Schema(description = "The id of the player")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="playerId")
    private Playerdto playerdto;

}
