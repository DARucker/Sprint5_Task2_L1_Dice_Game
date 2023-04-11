package com.sprint5.task2.level1.dice.game.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="players")
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    @OneToMany(mappedBy="Player")
    private List<Game> games;


}
