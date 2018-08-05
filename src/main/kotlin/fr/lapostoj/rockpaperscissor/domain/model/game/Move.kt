package fr.lapostoj.rockpaperscissor.domain.model.game

data class Move(
    val playerId: PlayerId,
    val value: MoveValue
)