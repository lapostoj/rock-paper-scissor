package fr.lapostoj.rockpaperscissor.domain.model.game

import fr.lapostoj.rockpaperscissor.factory.aMove
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class MoveTest: Spek({
    given("a rock move") {
        val move = aMove()

        it("should compare correctly with a rock move") {
            assertEquals(move.compareTo(aMove()), 0)
        }

        it("should compare correctly with a paper move") {
            assertTrue(move < aMove(PlayerId(1), MoveValue.PAPER))
        }

        it("should compare correctly with a scissor move") {
            assertTrue(move > aMove(PlayerId(1), MoveValue.SCISSOR))
        }
    }

    given("a paper move") {
        val move = aMove(PlayerId(1), MoveValue.PAPER)

        it("should compare correctly with a rock move") {
            assertTrue(move > aMove())
        }

        it("should compare correctly with a paper move") {
            assertEquals(move.compareTo(aMove(PlayerId(1), MoveValue.PAPER)), 0)
        }

        it("should compare correctly with a scissor move") {
            assertTrue(move < aMove(PlayerId(1), MoveValue.SCISSOR))
        }
    }

    given("a scissor move") {
        val move = aMove(PlayerId(1), MoveValue.SCISSOR)

        it("should compare correctly with a rock move") {
            assertTrue(move < aMove())
        }

        it("should compare correctly with a paper move") {
            assertTrue(move > aMove(PlayerId(1), MoveValue.PAPER))
        }

        it("should compare correctly with a scissor move") {
            assertEquals(move.compareTo(aMove(PlayerId(1), MoveValue.SCISSOR)), 0)
        }
    }
})