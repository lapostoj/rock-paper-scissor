package fr.lapostoj.rockpaperscissor.configuration

import fr.lapostoj.rockpaperscissor.application.service.CreateGame
import fr.lapostoj.rockpaperscissor.application.service.GetGame
import fr.lapostoj.rockpaperscissor.application.service.PlayMove
import fr.lapostoj.rockpaperscissor.domain.model.game.GameRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import

@Configuration
@Import(RepositoryConfiguration::class)
class ServicesConfiguration {
    @Autowired
    lateinit var gameRepository: GameRepository

    @Bean
    fun createGame(): CreateGame {
        return CreateGame(gameRepository)
    }

    @Bean
    fun getGame(): GetGame {
        return GetGame(gameRepository)
    }

    @Bean
    fun playMove(): PlayMove {
        return PlayMove(gameRepository)
    }
}
