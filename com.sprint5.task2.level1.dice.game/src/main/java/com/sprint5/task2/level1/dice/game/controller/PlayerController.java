package com.sprint5.task2.level1.dice.game.controller;

import com.sprint5.task2.level1.dice.game.dto.Gamedto;
import com.sprint5.task2.level1.dice.game.dto.Playerdto;
import com.sprint5.task2.level1.dice.game.entity.Game;
import com.sprint5.task2.level1.dice.game.entity.Player;
import com.sprint5.task2.level1.dice.game.service.IGameService;
import com.sprint5.task2.level1.dice.game.service.IPlayerSevice;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
//@RequiredArgsConstructor
@RestController
@RequestMapping("/player")
@Tag(name = "Spring 5 - Task 2 - Dice Game", description = "")
public class PlayerController {

    private static Logger log = LoggerFactory.getLogger(PlayerController.class);
    @Autowired
    private IPlayerSevice playerSevice;
    @Autowired
    private IGameService gameService;

    /**
     * This method creates a Player
     * @param playerdto
     * @return ResponseEntity<Playerdto>
     */
    @PostMapping(value="/add")
    @Operation(summary= "Adds a new Player", description = "Creates a new player and saves it in the database")
    @ApiResponse(responseCode = "201", description = "Player created correctly", content = {@Content(mediaType = "application/json",
        schema = @Schema(implementation = Playerdto.class))})
    @ApiResponse(responseCode = "403", description = "The player already exists", content = @Content)
    public ResponseEntity<?> createPlayer(@RequestBody Playerdto playerdto){
        log.info("create player: " + playerdto);
        try {
            playerSevice.create(playerdto);
            return ResponseEntity.ok(playerdto);
        }catch (ResponseStatusException e){
            Map<String, Object> error = new HashMap<>();
            error.put("Message", e.getMessage());
            error.put("Reason", e.getReason());
            return new ResponseEntity<Map<String,Object>>(error, HttpStatus.FORBIDDEN);
        }
    }


    /**
     * PUT /players: modifica el nombre del jugador/a.
     */

    @PutMapping(value="/update/")
    public ResponseEntity<?> updatePlayer(@RequestBody Playerdto playerdto){
        log.info("update player: " + playerdto);
        try {
            playerSevice.update(playerdto);
            return ResponseEntity.ok(playerdto);
        }catch (ResponseStatusException e){
            Map<String, Object> error = new HashMap<>();
            error.put("Message", e.getMessage());
            error.put("Reason", e.getReason());
            return new ResponseEntity<Map<String,Object>>(error, HttpStatus.NOT_FOUND);
        }

    }

    /**
     * GAMES
     * POST /players/{id}/games/ : un jugador/a específico realiza un tirón de los dados.
     */
    @Operation(summary= "Roll dices", description = "If dice 1 + dice 2 = 7, then the player wins. The result is saved in the database")
    @ApiResponse(responseCode = "200", description = "Game added to the database", content = {@Content(mediaType = "application/json",
            schema = @Schema(implementation = Gamedto.class))})
    @ApiResponse(responseCode = "404", description = "Player not found", content = @Content)
    @PostMapping("/{id}/games/")
    public ResponseEntity<?> rollDice(@PathVariable int id){
        Playerdto playerPlaying;
        Gamedto gamedto;
        try {
            playerPlaying = playerSevice.findById(id);
        }catch(ResponseStatusException e){
            Map<String, Object> error = new HashMap<>();
            error.put("Message", e.getMessage());
            error.put("Reason", e.getReason());
            return new ResponseEntity<Map<String,Object>>(error, HttpStatus.NOT_FOUND);
        }
        gamedto = gameService.playGame(playerPlaying);
        return ResponseEntity.ok(gamedto);
    }

    /**
     * GET /players/{id}/games: devuelve el listado de jugadas por un jugador/a.
     * @param id
     * @return
     */
    @Operation(summary= "Lista todas las jugadas de un jugador", description = "Retorna el listado de jugadas hechas por un jugador")
    @ApiResponse(responseCode = "2XX", description = "Listado de jugadas", content = {@Content(mediaType = "application/json",
            schema = @Schema(implementation = Gamedto.class))})
    @ApiResponse(responseCode = "404", description = "Player not found", content = @Content)

    @GetMapping("/{id}/games/")
    public ResponseEntity<?> findAllGames(@PathVariable int id) {
        List<Gamedto> gamedtos;
        try {
            gamedtos = gameService.findAllByPlayerId(id);
        } catch (ResponseStatusException e) {
            Map<String, Object> error = new HashMap<>();
            error.put("Message", e.getMessage());
            error.put("Reason", e.getReason());
            return new ResponseEntity<Map<String, Object>>(error, HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(gamedtos);
    }

}
