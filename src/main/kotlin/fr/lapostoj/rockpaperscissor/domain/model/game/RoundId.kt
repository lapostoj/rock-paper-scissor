package fr.lapostoj.rockpaperscissor.domain.model.game

import java.util.*

class RoundId(val value: UUID) {
    companion object {
        fun nextId(): RoundId {
            return RoundId(UUID.randomUUID())
        }
    }
}