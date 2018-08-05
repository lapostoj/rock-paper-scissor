package fr.lapostoj.rockpaperscissor.domain.model.game

data class Move(
    val playerId: PlayerId,
    val value: MoveValue
) {
    fun compare(otherMove: Move): Int {
        if (value == MoveValue.ROCK) {
            if (otherMove.value == MoveValue.ROCK) {
                return 0
            }
            if (otherMove.value == MoveValue.PAPER) {
                return -1
            }
            if (otherMove.value == MoveValue.SCISSOR) {
                return 1
            }
        }
        if (value == MoveValue.PAPER) {
            if (otherMove.value == MoveValue.ROCK) {
                return 1
            }
            if (otherMove.value == MoveValue.PAPER) {
                return 0
            }
            if (otherMove.value == MoveValue.SCISSOR) {
                return -1
            }
        }
        if (value == MoveValue.SCISSOR) {
            if (otherMove.value == MoveValue.ROCK) {
                return -1
            }
            if (otherMove.value == MoveValue.PAPER) {
                return 1
            }
            if (otherMove.value == MoveValue.SCISSOR) {
                return 0
            }
        }
        throw RuntimeException("Invalid move comparison")
    }
}