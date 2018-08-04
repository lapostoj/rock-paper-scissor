package fr.lapostoj.rockpaperscissor.domain.model.game

import fr.lapostoj.rockpaperscissor.factory.aMove
import fr.lapostoj.rockpaperscissor.factory.aRound
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.*
import kotlin.test.assertFailsWith
import kotlin.test.assertTrue

class RoundTest: Spek({
    given("a round") {
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
    }
})