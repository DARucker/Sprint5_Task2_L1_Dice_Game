package com.sprint5.task2.level1.dice.game.service;

import com.sprint5.task2.level1.dice.game.dto.Gamedto;
import com.sprint5.task2.level1.dice.game.dto.Playerdto;
import com.sprint5.task2.level1.dice.game.entity.Game;
import com.sprint5.task2.level1.dice.game.entity.Player;
import com.sprint5.task2.level1.dice.game.repository.GameRepository;
import com.sprint5.task2.level1.dice.game.repository.PlayerRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class GameServiceImpl implements IGameService {

    private static final Logger log = LoggerFactory.getLogger(GameServiceImpl.class);
    @Autowired
    private final GameRepository gameRepository;
    @Bean
    public ModelMapper modelMapper1() {
        return new ModelMapper();
    }
    public GameServiceImpl(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @Autowired
    private PlayerServiceImpl playerService;

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
        Game savedGame = new Game();
        savedGame = saveGame(gamedto);
        Gamedto savedGamedto = entityToDto(savedGame);
        return savedGamedto;

    }

    @Override
    public Game saveGame(Gamedto gamedto){
        Game saved = dtoToEntity(gamedto);
        saved.setPlayer(playerService.dtoToEntity(gamedto.getPlayerdto()));
        gameRepository.save(saved);
        return saved;
    }

    /**
     * findAllByPlayerId
     * @param playerId
     * @return
     */
    @Override
    public List<Gamedto> findAllByPlayerId(int playerId) {
        List<Game> gamesByPlayerId = gameRepository.findAllByPlayerId(playerId);
        if(gamesByPlayerId.isEmpty()){
            log.info("There are no games por player with id: " + playerId);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No player was found with id: " + playerId);
        }
        List<Gamedto> gamedtosByPlayerId = new ArrayList<>();
        for(Game g : gamesByPlayerId){
            Gamedto gamedto = entityToDto(g);
            gamedtosByPlayerId.add(gamedto);
        }
        return gamedtosByPlayerId;
    }

    /**
     * Roll dice and calculates if the player wins or looses this roll
     * return dice 1 plus dice 2. Must be between 2 and 12.
     */

    @Override
    public int rollDice() {

        int dice1 = (int) (Math.random()*6);
        int dice2 = (int) (Math.random()*6);
        return dice1+dice2;
    }

    /**
     * This method recibes a Game and return a DTO objecto of Game
     * @param game
     * @return gamedto
     */
    @Override
    public Gamedto entityToDto(Game game) {
        Gamedto gamedto = modelMapper1().map(game, Gamedto.class);
        return gamedto;
    }

    /**
     * This method recives a DTO objecto to trasnform it into an entity
     * @param gamedto
     * @return game
     */
    @Override
    public Game dtoToEntity(Gamedto gamedto) {
        Game game = modelMapper1().map(gamedto, Game.class);
        return game;
    }
}