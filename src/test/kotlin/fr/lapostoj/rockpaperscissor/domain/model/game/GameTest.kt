package fr.lapostoj.rockpaperscissor.domain.model.game

import fr.lapostoj.rockpaperscissor.factory.aGame
import fr.lapostoj.rockpaperscissor.factory.aRound
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on
import kotlin.test.assertFailsWith

class GameTest: Spek({
    describe("a game") {
        val game = aGame()

        on("applyRound") {
            val round = aRound()

            it("should throw exception if the round is not complete") {
                assertFailsWith(RuntimeException::class) {
                    game.applyRound(round)
                }
            }
        }
    }
})