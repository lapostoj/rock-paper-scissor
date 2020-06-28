package fr.lapostoj.rockpaperscissor.domain.model.game

data class Move(
    val playerId: PlayerId,
    val value: MoveValue
) : Comparable<Move> {

    override fun compareTo(other: Move): Int {
        if (value == MoveValue.ROCK) {
            if (other.value == MoveValue.ROCK) {
                return 0
            }
            if (other.value == MoveValue.PAPER) {
                return -1
            }
            if (other.value == MoveValue.SCISSOR) {
                return 1
            }
        }
        if (value == MoveValue.PAPER) {
            if (other.value == MoveValue.ROCK) {
                return 1
            }
            if (other.value == MoveValue.PAPER) {
                return 0
            }
            if (other.value == MoveValue.SCISSOR) {
                return -1
            }
        }
        if (value == MoveValue.SCISSOR) {
            if (other.value == MoveValue.ROCK) {
                return -1
            }
            if (other.value == MoveValue.PAPER) {
                return 1
            }
            if (other.value == MoveValue.SCISSOR) {
                return 0
            }
        }
        throw RuntimeException("Invalid move comparison")
    }
}
