package fr.lapostoj.rockpaperscissor.application.service

import fr.lapostoj.rockpaperscissor.domain.model.game.*
import fr.lapostoj.rockpaperscissor.domain.model.game.PlayerId
import fr.lapostoj.rockpaperscissor.presentation.api.command.PlayMoveCommand
import fr.lapostoj.rockpaperscissor.presentation.api.response.MoveResponse
import fr.lapostoj.rockpaperscissor.presentation.api.response.MoveToMoveResponse
import org.slf4j.LoggerFactory

class PlayMove(
    private val gameRepository: GameRepository
) {
    private val log = LoggerFactory.getLogger(PlayMove::class.java)

    fun forCommand(gameId: Long, playMoveCommand: PlayMoveCommand): MoveResponse {
        log.info("Play move for gameId {} from command {}", gameId, playMoveCommand)

        val game = gameRepository.findById(GameId(gameId))
            ?: throw GameNotFoundException("Game of id $gameId not found")

        val move = Move(
            PlayerId(playMoveCommand.playerId),
            MoveValue.valueOf(playMoveCommand.moveValue.name)
        )

        game.playMove(move)
        gameRepository.save(game)

        return MoveToMoveResponse.transform(move)
    }
}