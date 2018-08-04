package fr.lapostoj.rockpaperscissor.domain.model.game

import fr.lapostoj.rockpaperscissor.factory.aGame
import fr.lapostoj.rockpaperscissor.infrastructure.persistence.GameRepositoryImpl
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on

class GameRepositoryTest: Spek({
    describe("a game repository") {
        val gameRepository = GameRepositoryImpl()

        on("nextId") {
            it("should return the incremented") {
                assert(gameRepository.nextId() == GameId(1))
                assert(gameRepository.nextId() == GameId(2))
                assert(gameRepository.nextId() == GameId(3))
            }
        }

        on("save") {
            val game = aGame()
            val persistedGame = gameRepository.save(game)

            it("should return the saved game") {
                assert(persistedGame == game)
            }

            it("should have persisted the passed game") {
                val retrievedGame = gameRepository.findById(game.id)

                assert(retrievedGame == game)
            }
        }

        on("findById") {
            val game = aGame()
            gameRepository.save(game)

            it("should return the game of the passed id if it exists") {
                val retrievedGame = gameRepository.findById(game.id)

                assert(retrievedGame == game)
            }

            it("should return null if not game exists for the passed id") {
                val retrievedGame = gameRepository.findById(GameId(999))

                assert(retrievedGame == null)
            }
        }
    }
})