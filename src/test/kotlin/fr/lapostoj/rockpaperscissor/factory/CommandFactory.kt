package fr.lapostoj.rockpaperscissor.factory

import fr.lapostoj.rockpaperscissor.presentation.api.command.CreateGameCommand
import fr.lapostoj.rockpaperscissor.presentation.api.command.MoveValue
import fr.lapostoj.rockpaperscissor.presentation.api.command.PlayMoveCommand

fun aCreateGameCommand(
    playerIds: List<Long> = listOf(50, 51),
    winningScore: Int = 5
) = CreateGameCommand (
    playerIds = playerIds,
    winningScore = winningScore
)

fun aPlayMoveCommand(
    playerId: Long = 1,
    moveValue: MoveValue = MoveValue.ROCK
) = PlayMoveCommand(
    playerId = playerId,
    moveValue = moveValue
)