package fr.lapostoj.rockpaperscissor.domain.model.game

import fr.lapostoj.rockpaperscissor.factory.aMove
import fr.lapostoj.rockpaperscissor.factory.aRound
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertNotNull
import kotlin.test.assertNull
import kotlin.test.assertTrue

class RoundTest : Spek({
    describe("a round") {
        var round = aRound()

        context("addMove") {
            val move = aMove()

            beforeEachTest {
                round = aRound()
            }

            it("should add the move to the moves list") {
                round.addMove(move)

                assertTrue(round.moves.contains(move))
            }

            it("should throw InvalidMoveException if the round already contains a move from the same player") {
                round.addMove(move)

                assertFailsWith(InvalidMoveException::class) {
                    round.addMove(move)
                }
            }
        }

        context("getWinner") {
            val winnerId = PlayerId(2)

            beforeEachTest {
                round = aRound()
            }

            it("should return the id of the winner of the round") {
                val rockMove = aMove()
                val paperMove = aMove(winnerId, MoveValue.PAPER)
                round.addMove(rockMove)
                round.addMove(paperMove)

                val id = round.getWinner()

                assertNotNull(id)
                assertEquals(id, winnerId)
            }

            it("should return null if all players played the same move") {
                val rockMove1 = aMove()
                val rockMove2 = aMove(winnerId, MoveValue.ROCK)
                round.addMove(rockMove1)
                round.addMove(rockMove2)

                val id = round.getWinner()

                assertNull(id)
            }

            it("should return null if the round is empty") {
                val id = round.getWinner()

                assertNull(id)
            }

            it("should return null if the round is incomplete") {
                round.addMove(aMove())

                val id = round.getWinner()

                assertNull(id)
            }
        }
    }
})
