package fr.lapostoj.rockpaperscissor.infrastructure.persistence

import fr.lapostoj.rockpaperscissor.domain.model.game.Game
import fr.lapostoj.rockpaperscissor.domain.model.game.GameId
import fr.lapostoj.rockpaperscissor.domain.model.game.GameRepository

class InMemoryGameRepository : GameRepository {
    private val inMemoryStorage: MutableMap<Long, Game> = hashMapOf()
    private var idSequence = 1.toLong()

    override fun nextId(): GameId {
        return GameId(idSequence++)
    }

    override fun save(game: Game): Game {
        inMemoryStorage[game.id.value] = game
        return game
    }

    override fun findById(id: GameId): Game? {
        return inMemoryStorage[id.value]
    }
}
