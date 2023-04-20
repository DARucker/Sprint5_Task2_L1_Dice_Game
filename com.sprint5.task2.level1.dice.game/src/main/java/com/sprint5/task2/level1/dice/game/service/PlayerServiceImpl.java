package com.sprint5.task2.level1.dice.game.service;

import com.sprint5.task2.level1.dice.game.dto.Gamedto;
import com.sprint5.task2.level1.dice.game.dto.Playerdto;
import com.sprint5.task2.level1.dice.game.entity.Player;
import com.sprint5.task2.level1.dice.game.repository.PlayerRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;
import org.springframework.web.server.ResponseStatusException;


import java.util.Calendar;
import java.util.List;
import java.util.Optional;

//@RequiredArgsConstructor
@Service
public class PlayerServiceImpl implements IPlayerSevice {

    private static final Logger log = LoggerFactory.getLogger(PlayerServiceImpl.class);
    @Autowired
    private final PlayerRepository playerRepository;
    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
    public PlayerServiceImpl(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @Override
    public Player create(Playerdto playerdto) {
            if(!playerdto.getName().equals("")) {
                Optional<Player> playerDb = playerRepository.findByName(playerdto.getName());
                if (playerDb.isPresent()) {
                    throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Te player with name " + playerdto.getName() + " exists.");
                }
            }
            Playerdto playerCreate = new Playerdto();
            playerCreate.setName(playerdto.getName());
            playerCreate.setRegistDate(Calendar.getInstance());
            return playerRepository.save(dtoToEntity(playerCreate));
    }

    @Override
    public Playerdto findById(int id) {
        Optional<Player> player = playerRepository.findById(id);
        if(player.isPresent()){
            log.info("player found with id: " + id);
            return entityToDto(player.get());
        }else {
            log.info("No player was found with id: " + id);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No player was found with id: " + id);
        }
    }

    @Override
    public List<Player> findAll() {

        return playerRepository.findAll();
    }

    /**
     * This method updates the name of the player
     * @param playerdto
     * @return playerdto
     */
    @Override
    public Playerdto update(Playerdto playerdto) {
            log.info("update player: " + playerdto);
            Optional<Player> playerDb = playerRepository.findById(playerdto.getId());
            if (!playerDb.isPresent()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Can´t find player with id " + playerdto.getId());
            }
            Optional<Player> playerDb1 = playerRepository.findByName(playerdto.getName());
            if (!playerDb.isPresent()) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "The name " + playerdto.getName() + " is in use");
            }
            Player playerUpdate = playerDb.get();
            playerUpdate.setName(playerdto.getName());
            playerUpdate = playerRepository.save(playerUpdate);
            return entityToDto(playerUpdate);
    }

    @Override
    public void delete(int id) {

    }

    /**
     * This method search for the player into the database
     * @param name
     * @return Playerdto
     * @throws ResponseStatusException
     */
    @Override
    public Playerdto findByName(String name) throws ResponseStatusException {
        Optional<Player> player = playerRepository.findByName(name);
        if(player.isPresent()){
            log.info("player found with name: " + name);
            return entityToDto(player.get());
        }else {
            log.info("No player was found with name: " + name);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No player was found with name: " + name);
        }
    }

    /**
     * This method transform an entity into a DTO
     * @param player
     * @return playerdto
     */
    @Override
    public Playerdto entityToDto(Player player) {
        Playerdto playerdto = modelMapper().map(player, Playerdto.class);
        if(playerdto.getName().equals("")){
            playerdto.setName("Anonymous");
        }
        return playerdto;
    }
    /**
     * This method recives a DTO object to transform it into an entity
     * @param playerdto
     * @return player
     */
    @Override
    public Player dtoToEntity(Playerdto playerdto) {
        Player player = modelMapper().map(playerdto, Player.class);
        return player;
    }
}
