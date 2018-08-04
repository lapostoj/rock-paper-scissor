package fr.lapostoj.rockpaperscissor.domain.model.game

interface MoveRepository {
    fun nextId(): MoveId
}