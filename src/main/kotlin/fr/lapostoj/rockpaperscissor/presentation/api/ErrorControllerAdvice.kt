package fr.lapostoj.rockpaperscissor.presentation.api

import fr.lapostoj.rockpaperscissor.domain.model.game.GameFinishedException
import fr.lapostoj.rockpaperscissor.domain.model.game.GameNotFoundException
import fr.lapostoj.rockpaperscissor.domain.model.game.InvalidMoveException
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ErrorControllerAdvice {
    private val log = LoggerFactory.getLogger(GameController::class.java)

    @ExceptionHandler(RuntimeException::class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    fun unexpectedRuntimeException(e: RuntimeException): String {
        log.error("Unexpected exception", e)
        return "unexpected.error"
    }

    @ExceptionHandler(GameNotFoundException::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun gameNotFoundException(e: GameNotFoundException): String {
        log.error("Game Not Found exception", e)
        return "game.not.found"
    }

    @ExceptionHandler(GameFinishedException::class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    fun gameFinishedException(e: GameFinishedException): String {
        log.error("Game Finished exception", e)
        return "game.finished"
    }

    @ExceptionHandler(InvalidMoveException::class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    fun invalidMoveException(e: InvalidMoveException): String {
        log.error("Invalid Move exception", e)
        return "invalid.move"
    }
}