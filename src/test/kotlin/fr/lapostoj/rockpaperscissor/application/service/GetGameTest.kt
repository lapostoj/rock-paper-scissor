package fr.lapostoj.rockpaperscissor.application.service

import fr.lapostoj.rockpaperscissor.domain.model.game.GameNotFoundException
import fr.lapostoj.rockpaperscissor.domain.model.game.GameRepository
import fr.lapostoj.rockpaperscissor.factory.aGame
import fr.lapostoj.rockpaperscissor.factory.aGameResponse
import io.mockk.every
import io.mockk.mockk
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class GetGameTest: Spek({
    describe("a get game service") {
        val gameRepository = mockk<GameRepository>()
        val service = GetGame(gameRepository)

        context("forId") {
            val gameId = 123L

            it("should return the game corresponding to the passed id") {
                every { gameRepository.findById(id = any()) } returns aGame()

                val gameResponse = service.forId(gameId)

                assertEquals(gameResponse, aGameResponse())
            }

            it("should throw GameNotFoundException if no game is found for the passed id") {
                every { gameRepository.findById(id = any()) } returns null

                assertFailsWith(GameNotFoundException::class) {
                    service.forId(gameId)
                }
            }
        }
    }
})