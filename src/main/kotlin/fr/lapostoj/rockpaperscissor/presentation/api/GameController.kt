package fr.lapostoj.rockpaperscissor.presentation.api

import fr.lapostoj.rockpaperscissor.application.service.CreateGame
import fr.lapostoj.rockpaperscissor.application.service.GetGame
import fr.lapostoj.rockpaperscissor.application.service.PlayMove
import fr.lapostoj.rockpaperscissor.presentation.api.command.CreateGameCommand
import fr.lapostoj.rockpaperscissor.presentation.api.command.PlayMoveCommand
import fr.lapostoj.rockpaperscissor.presentation.api.response.GameResponse
import fr.lapostoj.rockpaperscissor.presentation.api.response.MoveResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType.APPLICATION_JSON_VALUE
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/games", consumes = [APPLICATION_JSON_VALUE], produces = [APPLICATION_JSON_VALUE])
class GameController {
    @Autowired
    lateinit var createGame: CreateGame

    @Autowired
    lateinit var getGame: GetGame

    @Autowired
    lateinit var playMove: PlayMove

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    fun createGame(@RequestBody createGameCommand: CreateGameCommand): GameResponse {
        return createGame.forCommand(createGameCommand)
    }

    @GetMapping("/{gameId}")
    @ResponseStatus(HttpStatus.OK)
    fun getGame(@PathVariable("gameId") gameId: Long): GameResponse {
        return getGame.forId(gameId)
    }

    @PostMapping("/{gameId}/moves")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    fun playMove(@PathVariable gameId: Long, @RequestBody playMoveCommand: PlayMoveCommand): MoveResponse {
        return playMove.forCommand(gameId, playMoveCommand)
    }
}
