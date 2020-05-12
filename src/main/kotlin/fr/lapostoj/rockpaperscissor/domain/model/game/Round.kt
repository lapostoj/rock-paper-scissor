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
        return when {
            moves.size < 2 -> {
                return null
            }
            moves[0] > moves[1] -> {
                moves[0].playerId
            }
            moves[0] < moves[1] -> {
                moves[1].playerId
            }
            else -> {
                null
            }
        }
    }

    private fun validateMoveCanBeAdded(move: Move) {
        if (moves.map(Move::playerId).contains(move.playerId)) {
            throw InvalidMoveException("Player ${move.playerId.value} already played in this round")
        }
    }
}