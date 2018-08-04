package fr.lapostoj.rockpaperscissor.domain.model.game

import fr.lapostoj.rockpaperscissor.infrastructure.persistence.InMemoryMoveRepository
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on
import kotlin.test.assertEquals

class MoveRepositoryTest: Spek({
    describe("a game repository") {
        val moveRepository = InMemoryMoveRepository()

        on("nextId") {
            it("should return the incremented") {
                assertEquals(moveRepository.nextId(), MoveId(1))
                assertEquals(moveRepository.nextId(), MoveId(2))
                assertEquals(moveRepository.nextId(), MoveId(3))
            }
        }
    }
})