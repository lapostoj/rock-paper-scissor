package fr.lapostoj.rockpaperscissor.domain.model.game

import fr.lapostoj.rockpaperscissor.factory.aGame
import fr.lapostoj.rockpaperscissor.factory.aMove
import fr.lapostoj.rockpaperscissor.infrastructure.persistence.InMemoryGameRepository
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe
import kotlin.test.assertEquals

class GameRepositoryTest: Spek({
    describe("a game repository") {
        var gameRepository = InMemoryGameRepository()

        context("nextId") {
            beforeEachTest {
                gameRepository = InMemoryGameRepository()
            }

            it("should return the incremented id") {
                assertEquals(gameRepository.nextId(), GameId(1))
                assertEquals(gameRepository.nextId(), GameId(2))
                assertEquals(gameRepository.nextId(), GameId(3))
            }
        }

        context("save") {
            val game = aGame()

            beforeEachTest {
                gameRepository = InMemoryGameRepository()
            }

            it("should return the saved game") {
                val persistedGame = gameRepository.save(game)

                assertEquals(persistedGame, game)
            }

            it("should have persisted the passed game") {
                gameRepository.save(game)
                val retrievedGame = gameRepository.findById(game.id)

                assertEquals(retrievedGame, game)
            }

            it("should update the game if saving an existing one") {
                val move = aMove()
                val persistedGame = gameRepository.save(game)
                persistedGame.playMove(move)

                gameRepository.save(persistedGame)
                val retrievedGame = gameRepository.findById(game.id) ?: throw GameNotFoundException("Broken test")

                assertEquals(retrievedGame.rounds[0].moves.size, 1)
                assertEquals(retrievedGame.rounds[0].moves[0], move)
            }
        }

        context("findById") {
            val game = aGame()

            beforeEachTest {
                gameRepository = InMemoryGameRepository()
                gameRepository.save(game)
            }

            it("should return the game of the passed id if it exists") {
                val retrievedGame = gameRepository.findById(game.id)

                assertEquals(retrievedGame, game)
            }

            it("should return null if no game exists for the passed id") {
                val retrievedGame = gameRepository.findById(GameId(999))

                assertEquals(retrievedGame, null)
            }
        }
    }
})