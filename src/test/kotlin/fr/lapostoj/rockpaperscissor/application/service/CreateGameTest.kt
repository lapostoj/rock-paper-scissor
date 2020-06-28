package fr.lapostoj.rockpaperscissor.application.service

import fr.lapostoj.rockpaperscissor.domain.model.game.GameId
import fr.lapostoj.rockpaperscissor.domain.model.game.GameRepository
import fr.lapostoj.rockpaperscissor.factory.aCreateGameCommand
import fr.lapostoj.rockpaperscissor.factory.aGame
import fr.lapostoj.rockpaperscissor.factory.aGameResponse
import fr.lapostoj.rockpaperscissor.presentation.api.response.GameResponse
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe
import kotlin.test.assertEquals

class CreateGameTest : Spek({
    describe("a create game service") {
        val gameRepository = mockk<GameRepository>()
        every { gameRepository.nextId() } returns GameId(123)
        every { gameRepository.save(game = any()) } returns aGame()
        val service = CreateGame(gameRepository)

        context("forCommand") {
            val gameResponse: GameResponse = service.forCommand(aCreateGameCommand())

            it("should persist the created game") {
                verify { gameRepository.nextId() }
                verify { gameRepository.save(game = any()) }
            }

            it("should return the create game as a response for the passed command") {
                assertEquals(gameResponse, aGameResponse())
            }
        }
    }
})
