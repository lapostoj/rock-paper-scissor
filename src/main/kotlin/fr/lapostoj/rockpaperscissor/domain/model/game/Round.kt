package fr.lapostoj.rockpaperscissor.domain.model.game

class Round(
    val id: RoundId,
    val gameId: GameId,
    val roundNumber: Int,
    val moves: MutableList<Move>
) {
    fun addMove(move: Move) {
        validateMoveCanBeAdded(move)
        moves.add(move)
    }

    private fun validateMoveCanBeAdded(move: Move) {
        if (moves.map(Move::playerId).contains(move.playerId)) {
            throw InvalidMoveException("Player ${move.playerId.value} already played in this round")
        }
    }
}