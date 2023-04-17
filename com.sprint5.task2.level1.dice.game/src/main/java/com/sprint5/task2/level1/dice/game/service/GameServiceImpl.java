package com.sprint5.task2.level1.dice.game.service;

import com.sprint5.task2.level1.dice.game.dto.Gamedto;
import com.sprint5.task2.level1.dice.game.dto.Playerdto;

public class GameServiceImpl implements IGameService {


    @Override
    public Gamedto playGame(Playerdto playerdto) {

        Gamedto gamedto = new Gamedto();
        gamedto.setPlayerdto(playerdto);
        gamedto.setPoints(rollDice());
        int points = rollDice();
        if(gamedto.getPoints()==7){
            gamedto.setResultGame("Win");
        }else {
            gamedto.setResultGame("Loose");
        }
        return gamedto;
    }


    /**
     * Tool methods
     *
     */

    @Override
    public int rollDice() {

        int dice1 = (int) (Math.random()*6);
        int dice2 = (int) (Math.random()*6);

        return dice1+dice2;
    }


}
