package org.man.bootkotlin.config

import org.man.bootkotlin.model.ResponseModel
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.client.HttpServerErrorException
import org.springframework.web.client.ResourceAccessException

@ControllerAdvice
class GlobalExceptionHandler {

    private val log = getLogger { }

    @ExceptionHandler(ResourceAccessException::class, HttpServerErrorException::class, HttpServerErrorException.GatewayTimeout::class)
    fun resourceRequestHandler(ex: Exception): ResponseEntity<ResponseModel<Any>> {
        log.error("Resource access exception: ", ex)
        return ResponseEntity(ResponseModel(2001), HttpStatus.SERVICE_UNAVAILABLE)
    }

    @ExceptionHandler(Exception::class)
    fun handleGenericException(ex: Exception): ResponseEntity<ResponseModel<Any>> {
        log.error("Exception: ", ex)
        return ResponseEntity(ResponseModel(1999), HttpStatus.INTERNAL_SERVER_ERROR)
    }

}
