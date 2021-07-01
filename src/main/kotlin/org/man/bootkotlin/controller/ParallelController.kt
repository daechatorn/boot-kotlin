package org.man.bootkotlin.controller

import org.man.bootkotlin.config.getLogger
import org.man.bootkotlin.model.ResponseModel
import org.man.bootkotlin.service.ParallelService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class ParallelController (
    val parallelService: ParallelService
) {

    private val log = getLogger { }

    @GetMapping("/v1/parallel/call")
    fun getPocCall(): ResponseEntity<ResponseModel<String>> {
        log.info("Incoming request on parallel call")
        val responseModel = ResponseModel<String>(1000)
        parallelService.testParallel()
        return ResponseEntity.ok(responseModel)
    }

}