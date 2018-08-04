package fr.lapostoj.rockpaperscissor.presentation.api.command

data class PlayMoveCommand(
    val playerId: Long,
    val moveValue: MoveValue
)