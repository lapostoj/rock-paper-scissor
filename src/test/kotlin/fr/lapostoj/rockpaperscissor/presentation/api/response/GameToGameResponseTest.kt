package fr.lapostoj.rockpaperscissor.presentation.api.response

import fr.lapostoj.rockpaperscissor.domain.model.game.PlayerId
import fr.lapostoj.rockpaperscissor.factory.aGame
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import kotlin.test.assertEquals

class GameToGameResponseTest: Spek({
    given("a game") {
        val game = aGame()
        it("should convert correctly to game response") {
            val gameResponse = GameToGameResponse.transform(game)

            assertEquals(gameResponse.id, game.id.value)
            assertEquals(gameResponse.winningScore, game.winningScore)
            assertEquals(gameResponse.playerIds, game.playerIds.map(PlayerId::value))
        }
    }
})