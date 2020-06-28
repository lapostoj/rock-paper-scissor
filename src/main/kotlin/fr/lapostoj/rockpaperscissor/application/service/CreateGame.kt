package fr.lapostoj.rockpaperscissor.application.service

import fr.lapostoj.rockpaperscissor.domain.model.game.Game
import fr.lapostoj.rockpaperscissor.domain.model.game.GameRepository
import fr.lapostoj.rockpaperscissor.domain.model.game.PlayerId
import fr.lapostoj.rockpaperscissor.presentation.api.command.CreateGameCommand
import fr.lapostoj.rockpaperscissor.presentation.api.response.GameResponse
import fr.lapostoj.rockpaperscissor.presentation.api.response.GameToGameResponse
import org.slf4j.LoggerFactory

class CreateGame(private val gameRepository: GameRepository) {
    private val log = LoggerFactory.getLogger(CreateGame::class.java)

    fun forCommand(createGameCommand: CreateGameCommand): GameResponse {
        log.info("Create game with command {}", createGameCommand)
        val game = Game(
            gameRepository.nextId(),
            createGameCommand.playerIds.map { PlayerId(it) },
            createGameCommand.winningScore,
            mutableListOf()
        )
        val savedGame = gameRepository.save(game)
        return GameToGameResponse.transform(savedGame)
    }
}
