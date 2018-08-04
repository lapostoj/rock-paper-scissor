package fr.lapostoj.rockpaperscissor.presentation.api.response

import fr.lapostoj.rockpaperscissor.factory.aMove
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import kotlin.test.assertEquals

class MoveToMoveResponseTest: Spek({
    given("a move") {
        val move = aMove()
        it("should convert correctly to move response") {
            val moveResponse = MoveToMoveResponse.transform(move)

            assertEquals(moveResponse.id, move.id.value)
            assertEquals(moveResponse.playerId, move.playerId.value)
            assertEquals(moveResponse.moveValue.name, move.value.name)
        }
    }
})