package fr.lapostoj.rockpaperscissor.domain.model.game

import fr.lapostoj.rockpaperscissor.factory.aGame
import fr.lapostoj.rockpaperscissor.infrastructure.persistence.InMemoryGameRepository
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.context
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import kotlin.test.assertEquals

class GameRepositoryTest: Spek({
    given("a game repository") {
        var gameRepository = InMemoryGameRepository()

        context("nextId") {
            beforeEachTest {
                gameRepository = InMemoryGameRepository()
            }

            it("should return the incremented") {
                assertEquals(gameRepository.nextId(), GameId(1))
                assertEquals(gameRepository.nextId(), GameId(2))
                assertEquals(gameRepository.nextId(), GameId(3))
            }
        }

        context("save") {
            beforeEachTest {
                gameRepository = InMemoryGameRepository()
            }

            val game = aGame()
            val persistedGame = gameRepository.save(game)

            it("should return the saved game") {
                assertEquals(persistedGame, game)
            }

            it("should have persisted the passed game") {
                val retrievedGame = gameRepository.findById(game.id)

                assertEquals(retrievedGame, game)
            }
        }

        context("findById") {
            beforeEachTest {
                gameRepository = InMemoryGameRepository()
            }

            val game = aGame()
            gameRepository.save(game)

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