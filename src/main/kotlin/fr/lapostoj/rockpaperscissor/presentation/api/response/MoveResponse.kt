package fr.lapostoj.rockpaperscissor.presentation.api.response

import fr.lapostoj.rockpaperscissor.presentation.api.command.MoveValue

data class MoveResponse(
    val playerId: Long,
    val moveValue: MoveValue
)