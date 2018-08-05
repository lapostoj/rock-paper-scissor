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

    fun getWinner(): PlayerId? {
        if (moves.size < 2){
            return null
        }
        if (moves[0].compare(moves[1]) > 0) {
            return moves[0].playerId
        } else if (moves[0].compare(moves[1]) < 0) {
            return moves[1].playerId
        } else {
            return null
        }
    }

    private fun validateMoveCanBeAdded(move: Move) {
        if (moves.map(Move::playerId).contains(move.playerId)) {
            throw InvalidMoveException("Player ${move.playerId.value} already played in this round")
        }
    }
}