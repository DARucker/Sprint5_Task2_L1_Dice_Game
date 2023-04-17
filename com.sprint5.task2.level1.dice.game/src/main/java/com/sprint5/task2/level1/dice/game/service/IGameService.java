package com.sprint5.task2.level1.dice.game.service;

import com.sprint5.task2.level1.dice.game.dto.Gamedto;
import com.sprint5.task2.level1.dice.game.dto.Playerdto;
import com.sprint5.task2.level1.dice.game.entity.Game;
import com.sprint5.task2.level1.dice.game.entity.Player;

public interface IGameService  {

    int rollDice();
    Gamedto playGame(Playerdto playerdto);
    Game saveGame(Gamedto gamedto);
    Gamedto entityToDto(Game game);
    Game dtoToEntity(Gamedto gamedto);


}
