package fr.lapostoj.rockpaperscissor.presentation.api.response

import fr.lapostoj.rockpaperscissor.domain.model.game.PlayerId
import fr.lapostoj.rockpaperscissor.factory.aGame
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe
import kotlin.test.assertEquals

class GameToGameResponseTest : Spek({
    describe("a game") {
        val game = aGame()
        it("should convert correctly to game response") {
            val gameResponse = GameToGameResponse.transform(game)

            assertEquals(gameResponse.id, game.id.value)
            assertEquals(gameResponse.winningScore, game.winningScore)
            assertEquals(gameResponse.playerIds, game.playerIds.map(PlayerId::value))
        }
    }
})
