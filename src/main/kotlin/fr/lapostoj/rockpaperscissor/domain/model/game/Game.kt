package fr.lapostoj.rockpaperscissor.domain.model.game

import fr.lapostoj.rockpaperscissor.domain.model.player.PlayerId

class Game(
    val id: GameId,
    val playerIds: List<PlayerId>,
    val winningScore: Int,
    val rounds: MutableList<Round>
) {
    fun applyRound(round: Round) {
        validateRoundCanBeApplied(round)
        rounds.add(round)
    }

    private fun validateRoundCanBeApplied(round: Round) {
        if (round.moves.size != playerIds.size) {
            throw RuntimeException("Incomplete round tried to be applied")
        }
    }
}

