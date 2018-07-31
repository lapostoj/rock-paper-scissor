package fr.lapostoj.rockpaperscissor.domain.model.game

import fr.lapostoj.rockpaperscissor.infrastructure.persistence.GameRepositoryImpl
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on

class GameRepositoryTest: Spek({
    describe("a game repository") {
        val gameRepository = GameRepositoryImpl()

        on("nextId") {
            it("should ") {

            }
        }
    }
})