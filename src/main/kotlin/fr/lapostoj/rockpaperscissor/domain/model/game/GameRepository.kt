package fr.lapostoj.rockpaperscissor.domain.model.game

interface GameRepository {
    fun nextId(): GameId
    fun save(game: Game): Game
    fun findById(id: GameId): Game?
}