package fr.lapostoj.rockpaperscissor.application.service

import fr.lapostoj.rockpaperscissor.domain.model.game.GameNotFoundException
import fr.lapostoj.rockpaperscissor.domain.model.game.GameRepository
import fr.lapostoj.rockpaperscissor.factory.aGame
import fr.lapostoj.rockpaperscissor.factory.aMoveResponse
import fr.lapostoj.rockpaperscissor.factory.aPlayMoveCommand
import fr.lapostoj.rockpaperscissor.presentation.api.response.MoveResponse
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class PlayMoveTest: Spek({
    describe("a play move service") {
        val gameRepository = mockk<GameRepository>()
        every { gameRepository.findById(id = any()) } returns aGame()
        every { gameRepository.save(game = any()) } returns aGame()
        val service = PlayMove(gameRepository)

        val gameId: Long = 123
        val playMoveCommand = aPlayMoveCommand()

        context("forCommand with valid gameId") {
            val moveResponse: MoveResponse = service.forCommand(gameId, playMoveCommand)

            it("should get the game of the passed id") {
                verify { gameRepository.findById(id = any()) }
            }

            it("should persist the game") {
                verify { gameRepository.save(game = any()) }
            }

            it("should return the create game as a response for the passed command") {
                assertEquals(moveResponse, aMoveResponse())
            }
        }

        context("forCommand with unknown gameId") {
            it("should throw a GameNotFoundException if no game is found for the passed id") {
                every { gameRepository.findById(id = any()) } returns null

                assertFailsWith(GameNotFoundException::class) {
                    service.forCommand(gameId, playMoveCommand)
                }
            }
        }
    }
})