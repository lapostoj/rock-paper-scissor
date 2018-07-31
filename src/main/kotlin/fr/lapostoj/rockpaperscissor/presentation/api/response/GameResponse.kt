package fr.lapostoj.rockpaperscissor.presentation.api.response

data class GameResponse(
    val playerIds: List<Long>,
    val winningScore: Int
)