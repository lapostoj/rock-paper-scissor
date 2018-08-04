package fr.lapostoj.rockpaperscissor.domain.model.game

data class Move(
    val id: MoveId,
    val playerId: PlayerId,
    val value: MoveValue
)