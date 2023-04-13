package com.sprint5.task2.level1.dice.game.service;

import com.sprint5.task2.level1.dice.game.dto.Playerdto;
import com.sprint5.task2.level1.dice.game.entity.Player;

import java.util.List;
import java.util.Optional;

public interface IPlayerSevice {

    Player create(Playerdto playerdto);
    Playerdto findById(int id);
    List<Playerdto> findAll();
    Playerdto update(Playerdto playerdto);
    void delete (int id);

    Playerdto findByName(String name);
    Playerdto entityToDto(Player player);
    Player dtoToEntity(Playerdto playerdto);


}
