package fr.lapostoj.rockpaperscissor.presentation.api.command

data class CreateGameCommand(
    val playerIds: List<Long>,
    val winningScore: Int
)
