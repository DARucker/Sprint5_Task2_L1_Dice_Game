package com.sprint5.task2.level1.dice.game.service;

import com.sprint5.task2.level1.dice.game.dto.Gamedto;
import com.sprint5.task2.level1.dice.game.dto.Playerdto;

public interface IGameService  {

    int rollDice();
    Gamedto playGame(Playerdto playerdto);


}
