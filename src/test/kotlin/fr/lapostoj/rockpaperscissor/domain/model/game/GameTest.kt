package fr.lapostoj.rockpaperscissor.domain.model.game

import fr.lapostoj.rockpaperscissor.factory.aGame
import fr.lapostoj.rockpaperscissor.factory.aGameWithNoRound
import fr.lapostoj.rockpaperscissor.factory.aMove
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.context
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class GameTest: Spek({
    given("a game with no round") {
        val game = aGameWithNoRound()

        context("playMove") {
            val move = aMove()
            it("should initialize the rounds") {
                game.playMove(move)

                assertEquals(game.rounds.size, 1)
            }
        }
    }

    given("a game with an incomplete round") {
        val game = aGame()
        val move1 = aMove(game.playerIds[0], MoveValue.ROCK)
        game.playMove(move1)

        context("playMove") {
            val move2 = aMove(game.playerIds[1], MoveValue.ROCK)
            game.playMove(move2)

            it("should add the move to the last round") {
                assertEquals(game.rounds.size, 1)
                assertEquals(game.rounds[0].moves.size, 2)
                assertEquals(game.rounds[0].moves[0], move1)
                assertEquals(game.rounds[0].moves[1], move2)
            }
        }
    }

    given("a game with a complete round") {
        val game = aGame()
        val move1 = aMove(game.playerIds[0], MoveValue.ROCK)
        val move2 = aMove(game.playerIds[1], MoveValue.ROCK)
        game.playMove(move1)
        game.playMove(move2)

        context("playMove") {
            val move3 = aMove(game.playerIds[0], MoveValue.ROCK)
            game.playMove(move3)

            it("should add a round") {
                assertEquals(game.rounds.size, 2)
                assertEquals(game.rounds[1].roundNumber, 2)
            }

            it("should add the move to the last round") {
                assertEquals(game.rounds[0].moves.size, 2)
                assertEquals(game.rounds[0].moves[0], move1)
                assertEquals(game.rounds[0].moves[1], move2)
                assertEquals(game.rounds[1].moves.size, 1)
                assertEquals(game.rounds[1].moves[0], move3)
            }
        }
    }

    given("an already finished game") {
        val game = aGame(GameId(123), listOf(PlayerId(1), PlayerId(2)), 1)
        val move1 = aMove(game.playerIds[0], MoveValue.ROCK)
        val move2 = aMove(game.playerIds[1], MoveValue.PAPER)
        game.playMove(move1)
        game.playMove(move2)

        context("playMove") {
            val move3 = aMove(game.playerIds[0], MoveValue.ROCK)

            it("should throw GameFinishedException") {
                assertFailsWith(GameFinishedException::class) {
                    game.playMove(move3)
                }
            }
        }
    }
})