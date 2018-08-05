package fr.lapostoj.rockpaperscissor.presentation.api

import com.fasterxml.jackson.databind.ObjectMapper
import fr.lapostoj.rockpaperscissor.application.service.CreateGame
import fr.lapostoj.rockpaperscissor.application.service.GetGame
import fr.lapostoj.rockpaperscissor.application.service.PlayMove
import fr.lapostoj.rockpaperscissor.domain.model.game.GameFinishedException
import fr.lapostoj.rockpaperscissor.domain.model.game.GameNotFoundException
import fr.lapostoj.rockpaperscissor.domain.model.game.InvalidMoveException
import fr.lapostoj.rockpaperscissor.factory.aCreateGameCommand
import fr.lapostoj.rockpaperscissor.factory.aGameResponse
import fr.lapostoj.rockpaperscissor.factory.aMoveResponse
import fr.lapostoj.rockpaperscissor.factory.aPlayMoveCommand
import fr.lapostoj.rockpaperscissor.presentation.api.command.CreateGameCommand
import fr.lapostoj.rockpaperscissor.presentation.api.command.PlayMoveCommand
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

class GameControllerTest: Spek({
    describe("a game controller") {
        val createGame = mockk<CreateGame>()
        val getGame = mockk<GetGame>()
        val playMove = mockk<PlayMove>()
        val controller = GameController()
        controller.createGame = createGame
        controller.getGame = getGame
        controller.playMove = playMove

        val objectMapper = ObjectMapper()
        val mvc = standaloneSetup(controller)
            .setControllerAdvice(ErrorControllerAdvice())
            .build()

        it("POST game should allow to create a game") {
            every { createGame.forCommand(createGameCommand = any()) } returns aGameResponse()
            val createGameCommand = aCreateGameCommand()

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

        it("GET game should allow to get a game") {
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

        it("GET game should return 404 if the game is not found") {
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

        it("POST move should allow to play a move") {
            every { playMove.forCommand(gameId = any(), playMoveCommand = any()) } returns aMoveResponse()
            val gameId = 123
            val playMoveCommand = aPlayMoveCommand()

            val response = mvc.perform(
                post("/v1/games/$gameId/moves")
                    .content(objectMapper.writeValueAsString(playMoveCommand))
                    .contentType(MediaType.APPLICATION_JSON_UTF8)
                    .accept(MediaType.APPLICATION_JSON_UTF8)
            )

            verify { playMove.forCommand(gameId = any(), playMoveCommand = any()) }
            response.andExpect(status().isCreated)
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.playerId").value(playMoveCommand.playerId))
                .andExpect(jsonPath("$.moveValue").value(playMoveCommand.moveValue.name))
        }

        it("POST move should return 404 if the game is not found") {
            every { playMove.forCommand(gameId = any(), playMoveCommand = any()) } throws GameNotFoundException("Game not found")
            val gameId = 123
            val playMoveCommand = aPlayMoveCommand()

            val response = mvc.perform(
                post("/v1/games/$gameId/moves")
                    .content(objectMapper.writeValueAsString(playMoveCommand))
                    .contentType(MediaType.APPLICATION_JSON_UTF8)
                    .accept(MediaType.APPLICATION_JSON_UTF8)
            )

            verify { playMove.forCommand(gameId = any(), playMoveCommand = any()) }
            response.andExpect(status().isNotFound)
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$").value("game.not.found"))
        }

        it("POST move should return 422 if the move is invalid") {
            every { playMove.forCommand(gameId = any(), playMoveCommand = any()) } throws InvalidMoveException("Invalid move")
            val gameId = 123
            val playMoveCommand = aPlayMoveCommand()

            val response = mvc.perform(
                post("/v1/games/$gameId/moves")
                    .content(objectMapper.writeValueAsString(playMoveCommand))
                    .contentType(MediaType.APPLICATION_JSON_UTF8)
                    .accept(MediaType.APPLICATION_JSON_UTF8)
            )

            verify { playMove.forCommand(gameId = any(), playMoveCommand = any()) }
            response.andExpect(status().isUnprocessableEntity)
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$").value("invalid.move"))
        }

        it("POST move should return 422 if the game is finished") {
            every { playMove.forCommand(gameId = any(), playMoveCommand = any()) } throws GameFinishedException("Game finished")
            val gameId = 123
            val playMoveCommand = aPlayMoveCommand()

            val response = mvc.perform(
                post("/v1/games/$gameId/moves")
                    .content(objectMapper.writeValueAsString(playMoveCommand))
                    .contentType(MediaType.APPLICATION_JSON_UTF8)
                    .accept(MediaType.APPLICATION_JSON_UTF8)
            )

            verify { playMove.forCommand(gameId = any(), playMoveCommand = any()) }
            response.andExpect(status().isUnprocessableEntity)
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$").value("game.finished"))
        }
    }
})