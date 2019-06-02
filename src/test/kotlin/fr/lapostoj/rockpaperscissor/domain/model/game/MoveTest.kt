package fr.lapostoj.rockpaperscissor.domain.model.game

import fr.lapostoj.rockpaperscissor.factory.aMove
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class MoveTest: Spek({
    describe("a rock move") {
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

    describe("a paper move") {
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

    describe("a scissor move") {
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