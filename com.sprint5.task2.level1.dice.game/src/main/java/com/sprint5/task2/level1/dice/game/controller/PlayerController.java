package com.sprint5.task2.level1.dice.game.controller;

import com.sprint5.task2.level1.dice.game.dto.Playerdto;
import com.sprint5.task2.level1.dice.game.service.IPlayerSevice;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/player")
@Tag(name = "Spring 5 - Task 2 - Dice Game", description = "")
public class PlayerController {

    private static Logger log = LoggerFactory.getLogger(PlayerController.class);
    @Autowired
    private IPlayerSevice playerSevice;

    /**
     * This class creates a Player
     * @param playerdto
     * @return ResponseEntity<Playerdto>
     */
    @PostMapping(value="/add")
    @Operation(summary= "Adds a new Player", description = "Creates a new player and saves it in the database")
    @ApiResponse(responseCode = "201", description = "Player created correctly", content = {@Content(mediaType = "application/json",
        schema = @Schema(implementation = Playerdto.class))})
    @ApiResponse(responseCode = "500", description = "Internal Server Error while crating the player", content = @Content)
    public ResponseEntity<Playerdto> createPlayer(@RequestBody Playerdto playerdto){

        playerSevice.create(playerdto);

        return null;
    }

}
