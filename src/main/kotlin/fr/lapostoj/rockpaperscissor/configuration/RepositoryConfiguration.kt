package fr.lapostoj.rockpaperscissor.configuration

import fr.lapostoj.rockpaperscissor.domain.model.game.GameRepository
import fr.lapostoj.rockpaperscissor.infrastructure.persistence.InMemoryGameRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class RepositoryConfiguration {
    @Bean
    fun gameRepository(): GameRepository {
        return InMemoryGameRepository()
    }
}