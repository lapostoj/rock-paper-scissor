package fr.lapostoj.rockpaperscissor.factory

import fr.lapostoj.rockpaperscissor.presentation.api.command.MoveValue
import fr.lapostoj.rockpaperscissor.presentation.api.response.GameResponse
import fr.lapostoj.rockpaperscissor.presentation.api.response.MoveResponse

fun aGameResponse(
    id: Long = 123,
    playerIds: List<Long> = listOf(50, 51),
    winningScore: Int = 5
) = GameResponse(
    id = id,
    playerIds = playerIds,
    winningScore = winningScore
)

fun aMoveResponse(
    id: Long = 456,
    playerId: Long = 1,
    moveValue: MoveValue = MoveValue.ROCK
) = MoveResponse(
    id = id,
    playerId = playerId,
    moveValue = moveValue
)