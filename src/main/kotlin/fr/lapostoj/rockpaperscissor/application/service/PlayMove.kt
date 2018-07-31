package fr.lapostoj.rockpaperscissor.application.service

import fr.lapostoj.rockpaperscissor.presentation.api.command.PlayMoveCommand
import fr.lapostoj.rockpaperscissor.presentation.api.response.MoveResponse
import org.slf4j.LoggerFactory

class PlayMove {
    private val log = LoggerFactory.getLogger(GetGame::class.java)

    fun execute(playMoveCommand: PlayMoveCommand): MoveResponse {
        log.info("Get game of id {}", playMoveCommand.moveValue)
        return MoveResponse(playMoveCommand.moveValue)
    }
}