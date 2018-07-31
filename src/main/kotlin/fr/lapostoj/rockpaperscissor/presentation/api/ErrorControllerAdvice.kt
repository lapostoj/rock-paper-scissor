package fr.lapostoj.rockpaperscissor.presentation.api

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
}