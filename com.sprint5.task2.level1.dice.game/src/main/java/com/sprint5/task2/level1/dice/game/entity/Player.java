package com.sprint5.task2.level1.dice.game.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.Calendar;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="players")
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;

    @Column(name = "registry_date")
    @Temporal(TemporalType.DATE)
    private Calendar registDate;

    @OneToMany(mappedBy="player", cascade = CascadeType.ALL)
    private List<Game> games;

    public Player(int id, String name){
        this.id = id;
        this.name = name;
    }

    public Player(List<Game> games) {
        this.games = games;
    }

    public Player(int id, String name, Calendar registDate) {
        this.id = id;
        this.name = name;
        this.registDate = registDate;
    }

}
