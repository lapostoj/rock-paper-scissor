package fr.lapostoj.rockpaperscissor.infrastructure.persistence

import fr.lapostoj.rockpaperscissor.domain.model.game.Game
import fr.lapostoj.rockpaperscissor.domain.model.game.GameId
import fr.lapostoj.rockpaperscissor.domain.model.game.GameRepository

class GameRepositoryImpl: GameRepository {
    override fun nextId(): GameId {
        return GameId(Math.random().toLong())
    }

    override fun save(game: Game): Game {
        return game
    }

    override fun findById(id: GameId): Game? {
        return null
    }
}