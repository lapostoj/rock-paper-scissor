package fr.lapostoj.rockpaperscissor.presentation.api.response

import fr.lapostoj.rockpaperscissor.factory.aMove
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe
import kotlin.test.assertEquals

class MoveToMoveResponseTest: Spek({
    describe("a move") {
        val move = aMove()
        it("should convert correctly to move response") {
            val moveResponse = MoveToMoveResponse.transform(move)

            assertEquals(moveResponse.playerId, move.playerId.value)
            assertEquals(moveResponse.moveValue.name, move.value.name)
        }
    }
})