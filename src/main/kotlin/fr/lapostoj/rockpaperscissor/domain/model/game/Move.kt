package fr.lapostoj.rockpaperscissor.domain.model.game

data class Move(
    val playerId: Long,
    val value: MoveValue
)