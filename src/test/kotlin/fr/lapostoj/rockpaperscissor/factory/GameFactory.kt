package fr.lapostoj.rockpaperscissor.factory

import fr.lapostoj.rockpaperscissor.domain.model.game.Game
import fr.lapostoj.rockpaperscissor.domain.model.game.GameId
import fr.lapostoj.rockpaperscissor.domain.model.game.Move
import fr.lapostoj.rockpaperscissor.domain.model.game.MoveValue
import fr.lapostoj.rockpaperscissor.domain.model.game.PlayerId
import fr.lapostoj.rockpaperscissor.domain.model.game.Round
import fr.lapostoj.rockpaperscissor.domain.model.game.RoundId
import java.util.UUID

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

fun aGameWithNoRound(
    id: GameId = GameId(123),
    playerIds: List<PlayerId> = listOf(PlayerId(50), PlayerId(51)),
    winningScore: Int = 5
) = Game(
    id = id,
    playerIds = playerIds,
    winningScore = winningScore,
    rounds = mutableListOf()
)

fun aRound(
    id: RoundId = RoundId(UUID.randomUUID()),
    gameId: GameId = GameId(123),
    roundNumber: Int = 1,
    moves: MutableList<Move> = mutableListOf()
) = Round(
    id = id,
    gameId = gameId,
    roundNumber = roundNumber,
    moves = moves
)

fun aMove(
    playerId: PlayerId = PlayerId(1),
    value: MoveValue = MoveValue.ROCK
) = Move(
    playerId = playerId,
    value = value
)
