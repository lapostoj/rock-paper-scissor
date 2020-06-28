package fr.lapostoj.rockpaperscissor.presentation.api.response

import fr.lapostoj.rockpaperscissor.domain.model.game.Move
import fr.lapostoj.rockpaperscissor.presentation.api.command.MoveValue

class MoveToMoveResponse {
    companion object {
        fun transform(move: Move): MoveResponse {
            return MoveResponse(move.playerId.value, MoveValue.valueOf(move.value.name))
        }
    }
}
