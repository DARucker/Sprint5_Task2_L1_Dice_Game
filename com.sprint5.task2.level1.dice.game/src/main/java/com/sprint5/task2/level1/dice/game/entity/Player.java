package com.sprint5.task2.level1.dice.game.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
//@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="players")
public class Player {

    public Player(int id, String name){
        this.id = id;
        this.name = name;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    @OneToMany//(mappedBy="Player")
    private List<Game> games;


}
