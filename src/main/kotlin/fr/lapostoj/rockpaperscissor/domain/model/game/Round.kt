package fr.lapostoj.rockpaperscissor.domain.model.game

class Round(
    val id: RoundId,
    val gameId: GameId,
    val roundNumber: Int,
    val moves: List<Move>
) {
}