package fr.lapostoj.rockpaperscissor.factory

import fr.lapostoj.rockpaperscissor.domain.model.game.*
import fr.lapostoj.rockpaperscissor.domain.model.player.PlayerId
import fr.lapostoj.rockpaperscissor.presentation.api.command.CreateGameCommand
import fr.lapostoj.rockpaperscissor.presentation.api.response.GameResponse

fun aCreateGameCommand(
    playerIds: List<Long> = listOf(50, 51),
    winningScore: Int = 5
) = CreateGameCommand (
    playerIds = playerIds,
    winningScore = winningScore
)

fun aGame(
    id: GameId = GameId(123),
    playerIds: List<PlayerId> = listOf(PlayerId(50), PlayerId(51)),
    winningScore: Int = 5,
    rounds: MutableList<Round> = mutableListOf(aRound())
) = Game(
    id = id,
    playerIds = playerIds,
    winningScore = winningScore,
    rounds = rounds
)

fun aRound(
    id: RoundId = RoundId(234),
    gameId: GameId = GameId(123),
    roundNumber: Int = 1,
    moves: List<Move> = listOf()
) = Round(
    id = id,
    gameId = gameId,
    roundNumber = roundNumber,
    moves = moves
)

fun aGameResponse(
    playerIds: List<Long> = listOf(50, 51),
    winningScore: Int = 5
) = GameResponse(
    playerIds = playerIds,
    winningScore = winningScore
)