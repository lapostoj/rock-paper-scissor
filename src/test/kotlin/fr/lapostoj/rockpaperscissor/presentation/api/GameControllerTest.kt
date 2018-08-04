package fr.lapostoj.rockpaperscissor.presentation.api

import com.fasterxml.jackson.databind.ObjectMapper
import fr.lapostoj.rockpaperscissor.application.service.CreateGame
import fr.lapostoj.rockpaperscissor.application.service.GetGame
import fr.lapostoj.rockpaperscissor.domain.model.game.GameNotFoundException
import fr.lapostoj.rockpaperscissor.factory.aGameResponse
import fr.lapostoj.rockpaperscissor.presentation.api.command.CreateGameCommand
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup

class GameControllerTest : Spek({
    describe("a game controller") {
        val createGame = mockk<CreateGame>()
        val getGame = mockk<GetGame>()
        val controller = GameController()
        controller.createGame = createGame
        controller.getGame = getGame
        val objectMapper = ObjectMapper()
        val mvc = standaloneSetup(controller)
            .setControllerAdvice(ErrorControllerAdvice())
            .build()

        it("POST should allow to create a game") {
            every { createGame.forCommand(createGameCommand = any()) } returns aGameResponse()
            val createGameCommand = CreateGameCommand(
                listOf(100, 101),
                5
            )

            val response = mvc.perform(
                post("/v1/games")
                    .content(objectMapper.writeValueAsString(createGameCommand))
                    .contentType(MediaType.APPLICATION_JSON_UTF8)
                    .accept(MediaType.APPLICATION_JSON_UTF8)
            )

            verify { createGame.forCommand(createGameCommand = any()) }
            response.andExpect(status().isCreated)
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.playerIds").isArray)
                .andExpect(jsonPath("$.playerIds[0]").value(50))
                .andExpect(jsonPath("$.playerIds[1]").value(51))
                .andExpect(jsonPath("$.winningScore").value(5))
        }

        it("GET should allow to get a game") {
            val id = 123L
            every { getGame.forId(gameId = any()) } returns aGameResponse()

            val response = mvc.perform(
                get("/v1/games/$id")
                    .contentType(MediaType.APPLICATION_JSON_UTF8)
                    .accept(MediaType.APPLICATION_JSON_UTF8)
            )

            verify { getGame.forId(gameId = any()) }
            response.andExpect(status().isOk)
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.playerIds").isArray)
                .andExpect(jsonPath("$.playerIds[0]").value(50))
                .andExpect(jsonPath("$.playerIds[1]").value(51))
                .andExpect(jsonPath("$.winningScore").value(5))
        }

        it("GET should return 404 if the game is not found") {
            val id = 123L
            every { getGame.forId(gameId = any()) } throws GameNotFoundException("Game not found")

            val response = mvc.perform(
                get("/v1/games/$id")
                    .contentType(MediaType.APPLICATION_JSON_UTF8)
                    .accept(MediaType.APPLICATION_JSON_UTF8)
            )

            verify { getGame.forId(gameId = any()) }
            response.andExpect(status().isNotFound)
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$").value("game.not.found"))
        }
    }
})