package fr.lapostoj.rockpaperscissor.infrastructure.persistence

import fr.lapostoj.rockpaperscissor.domain.model.game.*

class InMemoryMoveRepository: MoveRepository {
    private var idSequence = 1.toLong()

    override fun nextId(): MoveId {
        return MoveId(idSequence++)
    }
}