package fr.lapostoj.rockpaperscissor.presentation.api

import fr.lapostoj.rockpaperscissor.domain.model.game.GameFinishedException
import fr.lapostoj.rockpaperscissor.domain.model.game.GameNotFoundException
import fr.lapostoj.rockpaperscissor.domain.model.game.InvalidMoveException
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class ErrorControllerAdvice {
    private val log = LoggerFactory.getLogger(GameController::class.java)

    @ExceptionHandler(RuntimeException::class)
    fun unexpectedRuntimeException(e: RuntimeException): ResponseEntity<*> {
        log.error("Unexpected exception", e)
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body("unexpected.error")
    }

    @ExceptionHandler(GameNotFoundException::class)
    fun gameNotFoundException(e: GameNotFoundException): ResponseEntity<*> {
        log.error("Game Not Found exception", e)
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body("game.not.found")
    }

    @ExceptionHandler(GameFinishedException::class)
    fun gameFinishedException(e: GameFinishedException): ResponseEntity<*> {
        log.error("Game Finished exception", e)
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
            .body("game.finished")
    }

    @ExceptionHandler(InvalidMoveException::class)
    fun invalidMoveException(e: InvalidMoveException): ResponseEntity<*> {
        log.error("Invalid Move exception", e)
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
            .body("invalid.move")
    }
}