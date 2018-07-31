package fr.lapostoj.rockpaperscissor.configuration

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import springfox.documentation.builders.ApiInfoBuilder
import springfox.documentation.builders.PathSelectors
import springfox.documentation.service.ApiInfo
import springfox.documentation.service.Contact
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.OffsetDateTime
import java.time.ZonedDateTime
import java.util.*

@Configuration
@EnableSwagger2
class SwaggerConfiguration {
    private var supportedPath: List<String> = listOf(
        "/v\\d+/games.*"
    )

    @Bean
    fun rockPaperScissorApi(): Docket {
        return Docket(DocumentationType.SWAGGER_2)
            .apiInfo(apiInfo())
            .select()
            .paths(PathSelectors.regex(supportedPath.joinToString("||" )))
            .build()
            .directModelSubstitute(LocalDate::class.java, String::class.java)
            .directModelSubstitute(LocalDateTime::class.java, Date::class.java)
            .directModelSubstitute(ZonedDateTime::class.java, Date::class.java)
            .directModelSubstitute(OffsetDateTime::class.java, Date::class.java)
    }

    private fun apiInfo(): ApiInfo {
        return ApiInfoBuilder()
            .title("Rock Paper Scissor API")
            .description("Play a Rock Paper Scissor game")
            .contact(Contact("lapostoj", "TBD", "jerome.lapostolet@gmail.com"))
            .version("0.1")
            .build()
    }
}