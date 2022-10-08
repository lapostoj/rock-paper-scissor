package fr.lapostoj.rockpaperscissor.domain.model.game

class Game(
    val id: GameId,
    val playerIds: List<PlayerId>,
    val winningScore: Int,
    var rounds: MutableList<Round>
) {
    fun playMove(move: Move) {
        validateGameIsNotFinished()
        addNewRoundIfNeeded()

        getLastRound().addMove(move)
    }

    private fun validateGameIsNotFinished() {
        playerIds.forEach { playerId ->
            if (winningScore == getScore(playerId)) {
                throw GameFinishedException("Player $playerId already won the game")
            }
        }
    }

    private fun getScore(playerId: PlayerId): Int {
        return rounds.filter { round -> playerId == round.getWinner() }.size
    }

    private fun addNewRoundIfNeeded() {
        if (rounds.size == 0 || getLastRound().moves.size == playerIds.size) {
            addNextRound()
        }
    }

    private fun getLastRound(): Round {
        return rounds.last()
    }

    private fun addNextRound() {
        rounds.add(Round(RoundId.nextId(), id, rounds.size + 1, mutableListOf()))
    }
}
