package fr.lapostoj.rockpaperscissor.presentation.api.response

import fr.lapostoj.rockpaperscissor.domain.model.game.Game

class GameToGameResponse {
    companion object {
        fun transform(game: Game): GameResponse {
            return GameResponse(game.id.value, game.playerIds.map { it.value }, game.winningScore)
        }
    }
}