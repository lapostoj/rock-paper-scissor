package fr.lapostoj.rockpaperscissor.application.service

import fr.lapostoj.rockpaperscissor.domain.model.game.GameId
import fr.lapostoj.rockpaperscissor.domain.model.game.GameNotFoundException
import fr.lapostoj.rockpaperscissor.domain.model.game.GameRepository
import fr.lapostoj.rockpaperscissor.presentation.api.response.GameResponse
import fr.lapostoj.rockpaperscissor.presentation.api.response.GameToGameResponse
import org.slf4j.LoggerFactory

class GetGame(private val gameRepository: GameRepository) {
    private val log = LoggerFactory.getLogger(GetGame::class.java)

    fun forId(gameId: Long): GameResponse {
        val game = gameRepository.findById(GameId(gameId))
            ?: throw GameNotFoundException("Game of id $gameId not found")

        return GameToGameResponse.transform(game)
    }
}
